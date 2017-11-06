package cn.tarena.jsoup;

import java.util.ArrayList;
import java.util.List;

public class fffff{
	public static void add(List list,Object s){
		list.add(s);
		
	}
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		add(list,10);
		String s = list.get(0);
		System.out.println(s);
	
	}
}
//Type safety: The method add(Object) belongs to the raw type List. References to generic type List<E> should be parameterized