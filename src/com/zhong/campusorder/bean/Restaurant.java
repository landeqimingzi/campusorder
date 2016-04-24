package com.zhong.campusorder.bean;

import android.media.Image;
import android.widget.ImageView;

/**
 * 
 * @author 懒得起名字
 *	餐厅模板类
 */
public class Restaurant {
	
	private String name = "xxxxxxx";
	private String time = "xx:xx-xx:xx";
	private String length = "xx-xx";
	private String fastlength = "xx-xx";
	private Image logo = null;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getFastlength() {
		return fastlength;
	}
	public void setFastlength(String fastlength) {
		this.fastlength = fastlength;
	}

	
}
