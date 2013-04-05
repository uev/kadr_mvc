package com.spring.mti.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.mti.model.Department;
import com.spring.mti.model.Employe;
import com.spring.mti.model.address.City;

public class LayoutServiceImpl implements LayoutService {

	@Override
	public List<Object> employeToMapJson(List<Employe> e){
		List<Object> answer = new ArrayList<Object>();
		for (Employe item : e){
			City c = item.getFk_city(); 
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("city", c.getName());
			map.put("region", c.getFk_region().getName());
			map.put("country", c.getFk_country().getName());
			Department d = item.getFk_department();
			if (d != null) {
				map.put("department", d.getDep_name());
			} else {
				map.put("department", "Не указан"); 
			}	
			map.put("fio", item.getFio());
			map.put("id", item.getId());
			answer.add(map);
		}
			return answer;
	}
}
