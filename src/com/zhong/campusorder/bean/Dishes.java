package com.zhong.campusorder.bean;

import com.zhong.campusorder.R;

public class Dishes {

	private String name;
	private String lable;
	private String saleNumber;
	private String price;
	private int img;
	
	public Dishes() {
		
		this.name = "��ţ���У�΢����������������";
		this.lable = "�����Ƽ�";
		this.price = "23.00";
		this.saleNumber = "����163��";
		this.img = R.drawable.mu;
	}
	


	public int getImg() {
		return img;
	}



	public void setImg(int img) {
		this.img = img;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
