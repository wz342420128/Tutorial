package com.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;
import com.jt.dubbo.pojo.Order;
import com.jt.dubbo.service.DubboOrderRestService;

@Controller
public class OrderController {
	
	@Autowired
	private DubboOrderRestService dubboOrderRestService;
	
	private static final ObjectMapper MAPPER=new ObjectMapper();
	
	/*
	 * 用来响应生成订单的请求。前台要求返回的结构：
	 * SysResult.ok(生成的订单编号)
	 */
	@RequestMapping("/order/submit")
	@ResponseBody
	public SysResult createOrder(Order order){
		Long userId=7L;
		order.setUserId(userId);
		try {
			//调用Dubbo层的方法,但是dubbo不支持对象结构的接参，
			//所以处理思路是将order对象转换成json传过去
			String json=MAPPER.writeValueAsString(order);
			String orderId=dubboOrderRestService.createOrder(json);
			return SysResult.oK(orderId);
		} catch (Exception e) {
			return SysResult.build(201,"订单提交失败");
		}
		
		
	}
	/*
	 * 用来转向订单成功页面的，对应的页面 success.jsp
	 */
	@RequestMapping("/order/success")
	public String goSuccess(){
		return "success";
	}

}
