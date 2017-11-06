package cn.tarena.jsoup;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class TestDemo1 {

	@Test
	public void fetch_All_document() throws Exception{
		String url="http://blog.sina.com.cn/s/blog_67c528700102x58z.html?tj=1";
		Connection connection=Jsoup.connect(url);
		Document d1=connection.get();
		System.out.println(d1);
		
	}
	
	@Test
	public void fetch_jd_itemTitle() throws Exception{
		String url="http://blog.csdn.net/qq_24037959/article/details/51849062";
		Connection connection=Jsoup.connect(url);
		Document d1=connection.get();
		
		Elements els=d1.select("p");
		for(Element el:els){
			System.out.println(el.text());
		}
	}
	
	
	public void fetch_jd_itemPrice() throws Exception{
		String url="https://item.jd.com/3749089.html";
		Connection connection=Jsoup.connect(url);
		Document d1=connection.get();
		
		Elements els=d1.select(".dd .p-price .price").select(".J-p-3749089");
		for(Element el:els){
			System.out.println(el);
		}
	}
	
	
	public void fetch_jd_itemPrice_ajax() throws Exception{
		String url="https://p.3.cn/prices/mgets?callback=jQuery2335535&type=1&area=1_72_2799_0&pdtk=&pduid=1619928122&pdpin=&pin=null&pdbp=0&skuIds=J_3749089&ext=11000000&source=item-pc";
		Connection connection=Jsoup.connect(url);
		String json=connection.ignoreContentType(true).execute().body();
		System.out.println(json);
	}
	
	
	public void fetch_jd_itemPage_allTitle() throws Exception{
		String url="https://list.jd.com/list.html?cat=670%2C671%2C1105&go=0";
		Connection connection=Jsoup.connect(url);
		Document d1=connection.get();
		
		Elements els=d1.select(".p-name");
		for(Element el:els){
			System.out.println(el.text());
		}
	}
	
	
	public void fetch_jd_itemcat_3_all() throws Exception{
		String url="https://www.jd.com/allSort.aspx";
		Connection connection=Jsoup.connect(url);
		Document d1=connection.get();
		ArrayList<String> urlList=new ArrayList<>();
		
		Elements els=d1.select(".clearfix dd a");
		for(Element el:els){
			String href=el.attr("href");
			if(href.startsWith("//list.jd.com/list.html?cat=")){
				urlList.add(href);
			}
		}
		System.out.println(urlList);
	}
	
	
	public void fetch_jd_itemallTitle() throws Exception{
		String url="http://list.jd.com/list.html?cat=737,794,877";
		Connection connection=Jsoup.connect(url);
		Document d1=connection.get();
		
		ArrayList<String> itemTitleList=new ArrayList<>();
		
		Element el=d1.select(".p-skip em b").get(0);
		int pageNum=Integer.parseInt(el.text());
		for(int i=0;i<pageNum;i++){
			String itemurl="http://list.jd.com/list.html?cat=737,794,877&page="+i;
			Connection ci=Jsoup.connect(itemurl);
			Document doc=connection.get();
			Elements titleEls=d1.select(".p-name");
			for(Element titleEl:titleEls){
				itemTitleList.add(titleEl.text());
			}
		}
	}
	
	@Test
	public void fetch_bdnm_allmessage() throws Exception{
		String url="https://bj.nuomi.com/326";
		Connection connection=Jsoup.connect(url);
		Document d1=connection.get();
		ArrayList<String> itemTitleList=new ArrayList<>();
		Element el=d1.select(".j-filter-items-ab").select(".filter-items-ab").select(".filter-content-ab").get(0);
		int pageNum=Integer.parseInt(el.text());
		for(int i=0;i<pageNum;i++){
			String itemurl="https://bj.nuomi.com/326-page"+i;
			Connection connection3=Jsoup.connect(url);
			Document d3=connection.get();
			Elements titleEls=d1.select("");
			for(Element titleEl:titleEls){
				
			}
		}
		
	}
	
	
	
	
}
