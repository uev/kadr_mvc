package com.spring.mti.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.mti.model.Answer;
import com.spring.mti.model.CertificationState;
import com.spring.mti.model.Department;
import com.spring.mti.model.Employe;
import com.spring.mti.model.address.City;
import com.spring.mti.model.security.Authorities;

public class LayoutServiceImpl implements LayoutService {

	private Map<String, Object> decorateMapEmploye(Employe item){
		City c = item.getFk_city();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("city", c.getName());
		map.put("region", c.getFk_region().getName());
		map.put("country", c.getFk_country().getName());
		Department d = item.getFk_department();
		if (d != null) {
			map.put("department", d.getName());
		} else {
			map.put("department", "Не указан"); 
		}	
		map.put("fio", item.getFio());
		return map;
	}
	
	@Override
	public List<Object> employeToMapJson(List<Employe> e){
		List<Object> answer = new ArrayList<Object>();
		for (Employe item : e){
			try {
				Map<String, Object> map = decorateMapEmploye(item);
				map.put("id",item.getId());
				answer.add(map);
			} catch (Exception exept){
				exept.printStackTrace();
			}
		}
			return answer;
	}
	
	@Override
	public List<Object> authorityToMapJson(List<Authorities> e){
		List<Object> answer = new ArrayList<Object>();
		for (Authorities item : e){
			Map<String, Object> map = new HashMap<String, Object>();
			Employe em = item.getFk_user().getFk_employe(); 
			if (em != null){
				map = decorateMapEmploye(em);
			} else {
				String non = "Поле не задано";
				map.put("city", non);
				map.put("region", non);
				map.put("country", non);
				map.put("department", non);
				map.put("fio", non);
			}	
			map.put("role", item.getFk_role().getContent());
			map.put("username", item.getFk_user().getUsername());
			map.put("id",item.getFk_user().getId());
			answer.add(map);
		}
			return answer;
	}
	
	@Override
	public Map<String, Object> QuestionProfileToMapJson(List<Answer> a){
		Map<String, Object> map = new HashMap<String, Object>()	;
		Answer afirst = a.get(0);
		map.put("category", afirst.getFk_Question().getFk_catgory().getCname());
		map.put("title", afirst.getFk_Question().getName());
		map.put("content", afirst.getFk_Question().getContent());
		map.put("id", afirst.getFk_Question().getId());
		map.put("count_answ", a.size());
		for (int i = 0; i < a.size(); i++){
			Answer item = a.get(i);
			map.put("ans".concat(Integer.toString(i)), Arrays.asList(item.getContent(),item.isValid()));
		}
			return map;
	}
	
	@Override
	public List<Object> decorateAnswersOnQuestions(List<CertificationState> c){
		List<Object> answers = new ArrayList<Object>();
		for (CertificationState item : c){
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				Answer a = item.getFk_answer();
				map.put("Question", a.getFk_Question().getName());
				map.put("answer", a.getContent());
				boolean valid = item.isValid();
				if (valid == true){
					if(valid == a.isValid()){
						map.put("valid", "Верно");
					} else {
						map.put("valid", "Не верно");
					}
					answers.add(map);
				}
			} catch (Exception exept){
				exept.printStackTrace();
			}
		}
			return answers;
	}
	
	@Override
	/*
	 * count - всего записей в базе
	 * step - левый\правый отступ в записях от current
	 * size - число записей на странице
	 * current - запрашиваемая страница 
	 */
	public List<Integer> generateNaviPagination(int count, int step, int size, int current){
		List<Integer> lst = new ArrayList<Integer>();
		Integer rstep = current+step; 
		Integer spage = count/size; 
		if (rstep > spage){
			rstep = spage; 
		}
		Integer lstep = current - step;
		for (int i = lstep; i <= rstep; i++) {
			if (i > 0){
				lst.add(i);
			}
		}
		System.out.println(count);
		System.out.println(current);
		System.out.println(lstep);
		System.out.println(rstep);
		return lst;
	}
}