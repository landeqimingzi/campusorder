package com.zhong.campusorder;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MerchantsActivity extends Activity{

	private final static String SORT_DATA[]={"全部分类","快餐","面馆","休闲小吃"};
	private final static String DIS_DATA[]={"500米","1000米","全部"};
	private final static String PRE_DATA[]={"新用户","满减","免配送费"};
	private final static String SCHOOL_DATA[]={"西京学院","西安邮电大学","陕西师范大学"};
	
	private static final int IMG_DATA[] = {R.drawable.me,R.drawable.me1,R.drawable.me2,R.drawable.me3,R.drawable.me4,R.drawable.me5,R.drawable.me6,R.drawable.me7};
	private static final String MERNAME_DATA[] = {"海港川菜馆","阿尔比私房菜","杨铭黄焖鸡","小长安家常菜","花村美食咖喱屋","济州客脆皮鸡","福味小厨","袁师傅腊汁肉夹馍"};
	
	private ListView showList;
	private Spinner sortSpi,disSpi,preSpi,schoolSpi;
	private ImageButton searchBtn;
	private ArrayList<Mer> list = new ArrayList<>();
	private ArrayAdapter sortAdapter,disAdapter,preAdapter,schoolAdapter;
	
	private RestaurantView res;
	private MerListAdapter mlAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.merchants);

		initResource();
		initSetting();
	}

	
	private void initResource() {

		showList = (ListView) findViewById(R.id.merchants_list_content);
		sortSpi = (Spinner) findViewById(R.id.merchants_sort_spinner);
		disSpi = (Spinner) findViewById(R.id.merchants_distance_spinner);
		preSpi = (Spinner) findViewById(R.id.merchants_preferential_spinner);
		schoolSpi = (Spinner) findViewById(R.id.title_spinner);
		
		searchBtn = (ImageButton) findViewById(R.id.title_search_btn);
		
		sortAdapter = new ArrayAdapter<String>(this,R.layout.merchants_spinner_text,SORT_DATA);
		disAdapter = new ArrayAdapter<String>(this,R.layout.merchants_spinner_text,DIS_DATA);
		preAdapter = new ArrayAdapter<String>(this,R.layout.merchants_spinner_text,PRE_DATA);
		schoolAdapter = new ArrayAdapter<String>(this,R.layout.merchants_spinner_text,SCHOOL_DATA);

		for (int i = 0; i < 8; i++) {
			Mer mer = new Mer();
			mer.img = IMG_DATA[i];
			mer.merName = MERNAME_DATA[i];
			list.add(mer);
		}
		res = new RestaurantView();
		mlAdapter = new MerListAdapter(list);
	}
	private void initSetting() {
		
		
		sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		disAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		preAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		sortSpi.setAdapter(sortAdapter);
		disSpi.setAdapter(disAdapter);
		preSpi.setAdapter(preAdapter);
		schoolSpi.setAdapter(schoolAdapter);
		
		sortSpi.setSelection(0);
		disSpi.setSelection(0);
		preSpi.setSelection(0);
		schoolSpi.setSelection(0);
		
		showList.setAdapter(mlAdapter);
//		showList.setDivider(null);
		showList.setDividerHeight(10);
		showList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

							Intent intent = new Intent(MerchantsActivity.this, RestaurantActivity.class);
							intent.putExtra("RestauranName",MERNAME_DATA[position]);
							intent.putExtra("ResImg", IMG_DATA[position]);
							startActivity(intent);
				
			}
		});
		
	}

	public class MerListAdapter extends BaseAdapter {
		
		ArrayList<Mer> list;
		
		public MerListAdapter(ArrayList<Mer> list){
			this.list = list;
		}
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			RestaurantView res;
			if(convertView == null){
				LayoutInflater inflater = LayoutInflater.from(MerchantsActivity.this);
				convertView = inflater.inflate(R.layout.merchants_list, null);
				res = new RestaurantView();
				res.name  = (TextView) convertView.findViewById(R.id.merchants_list_name);
				res.time  = (TextView) convertView.findViewById(R.id.merchants_list_name);
				res.lable  = (TextView) convertView.findViewById(R.id.merchants_list_name);
				res.length  = (TextView) convertView.findViewById(R.id.merchants_list_name);
				res.fastlength  = (TextView) convertView.findViewById(R.id.merchants_list_name);
				res.cost  = (TextView) convertView.findViewById(R.id.merchants_list_name);
				res.image = (ImageView) convertView.findViewById(R.id.merchants_list_image);
				
				convertView.setTag(res);
			}else{
				res = (RestaurantView) convertView.getTag();
			}
			
			res.name.setText(list.get(position).merName);
			
			res.image.setImageResource(list.get(position).img);
			
			
			return convertView;
		}


		
	} 

	class RestaurantView{
		ImageView image;
		TextView name,time,length,fastlength,lable,cost;
	}
	private class Mer{
		int img;
		String merName;
	}
}
