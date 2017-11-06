package com.jt.dubbo.pojo;

import org.apache.solr.client.solrj.beans.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jt.common.po.BasePojo;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Item extends BasePojo{

	@Field("id")
	private Long id;
	
	@Field("title")
	private String title;
	
	@Field("sellPoint")
	private String sellPoint;
	
	@Field("price")
	private Long price;
	
	@Field("image")
	private String image;
	
	private String[] images;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSellPoint() {
		return sellPoint;
	}


	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}


	public Long getPrice() {
		return price;
	}


	public void setPrice(Long price) {
		this.price = price;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String[] getImages() {
		if(null==this.image){
			return null;
		}
		return this.image.split(",");
	}


	public void setImages(String[] images) {
		this.images = images;
	}
	
	
}
