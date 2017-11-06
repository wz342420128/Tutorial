package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.User;

@Service
public class UserService {
	@Autowired
	private HttpClientService httpClientService;
	
	private static final ObjectMapper MAPPER=new ObjectMapper();

	public void doRegister(User user) throws Exception {
		String url="http://sso.jt.com/user/register";
		Map<String,String> map=new HashMap<>();
		map.put("username",user.getUsername());
		map.put("password",user.getPassword());
		map.put("phone",user.getPhone());
		map.put("email",user.getEmail());
		
		httpClientService.doPost(url,map,"utf-8");
	}

	public String login(User user) {
		String url="http://sso.jt.com/user/login";
		Map<String,String> map=new HashMap<>();
		map.put("u",user.getUsername());
		map.put("p",user.getPassword());
		
		try {
			String json=httpClientService.doPost(url,map,"utf-8");
			JsonNode jn=MAPPER.readTree(json);
			String ticket=jn.get("data").asText();
			return ticket;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
