package com.zhong.campusorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Text;

import com.zhong.campusorder.bean.Dishes;
import com.zhong.campusorder.bean.Restaurant;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RestaurantActivity extends Activity {
	
	public static final String action = "android.com.action";
	private static final String[] SORT_DATA = {"�����Ƽ�","�ؼ۲�ϵ","�ز�","���","�ײ�ϵ��","��������","�ۿ���Ʒ","��֪��"};
	private static final String[][] DISHES_NAME = {
		{"��ţ���У�΢����������","�������⣨����Ƣ��","��ʽ���ŷ�","������˿��","��֭�Ź�"},
		{"С�������","����������","����Ģ��","��֭ʨ��ͷ","����Ѽ��"},
		{"�������","��������","��������˿","������빽","�ձ�����"},
		{"���ţ��","�㹽����","��ʽ����","�������","�����μ���"},
		{"С�ݻ��˼�+2���������","��˺���˸Ƿ�+���¿���","����ʨ��ͷ+С��","������˿�Ƿ�+������Ʒ","���̼�˫���ײ�"},
		{"�Ǵ�С����","�ŷ�ʨ��ͷ","�ɹ�����˿","ũ��С����","�ݲ���˿"},
		{"��ɫë��","���빽�趹ѿ","��ը������","�⽷�䵰","�˱�����"},
	};
	private static final String[][] DISHES_LABLE = {
		{"�����Ƽ�","�����Ƽ�","�����Ƽ�","�����Ƽ�","�����Ƽ�"},
		{"�����Ƽ�","�����糱","�곤�Ƽ�","��ɫ��","�����Ƽ�"},
		{"�곤�Ƽ�","�����糱","�����Ƽ�","�����Ƽ�","�����Ƽ�"},
		{"��ɫ��","��ɫ��","�곤�Ƽ�","�����Ƽ�","�����Ƽ�"},
		{"��ֵ�ײ�","��ֵ�ײ�","��ֵ�ײ�","��ֵ�ײ�","��ֵ�ײ�"},
		{"�����Ƽ�","�����糱","��ɫ��","��ֵ�ײ�","�곤�Ƽ�"},
		{"�����ۿ�","�����ۿ�","�����ۿ�","�����ۿ�","�����ۿ�"}
		
	}; 
	private static final String[][] DISHES_PRICE = {
		{"23","14","12","16","20"},
		{"14","12","10","17","16"},
		{"13","10","8","12","14"},
		{"16","12","12","10","9"},
		{"18","16","20","15","18"},
		{"17","32","12","13","9"},
		{"7","8","9","10","9"}
		
	};
	private static final String[][] DISHES_SALENUM = {
		{"����163��","����203��","����258��","����234��","����221��"},
		{"����175��","����253��","����238��","����259��","����131��"},
		{"����103��","����182��","����154��","����161��","����218��"},
		{"����256��","����65��","����654��","����23��","����123��"},
		{"����115��","����206��","����94��","����15��","����45��"},
		{"����356��","����342��","����394��","����527��","����445��"},
		{"����56��","����35��","����65��","����20��","����18��"}
		
	};
	
	private static final int[][] DISHES_IMG = {
		{R.drawable.mu,R.drawable.mu1,R.drawable.mu12,R.drawable.mu3,R.drawable.mu4},
		{R.drawable.mu5,R.drawable.mu11,R.drawable.mu17,R.drawable.mu23,R.drawable.mu29},
		{R.drawable.mu6,R.drawable.mu12,R.drawable.mu18,R.drawable.mu24,R.drawable.mu},
		{R.drawable.mu7,R.drawable.mu13,R.drawable.mu19,R.drawable.mu25,R.drawable.mu1},
		{R.drawable.mu8,R.drawable.mu14,R.drawable.mu20,R.drawable.mu26,R.drawable.mu12},
		{R.drawable.mu9,R.drawable.mu15,R.drawable.mu21,R.drawable.mu27,R.drawable.mu3},
		{R.drawable.mu10,R.drawable.mu16,R.drawable.mu22,R.drawable.mu28,R.drawable.mu4}
	};
	
	
	private ArrayList<ArrayList<Dishes>> list = new ArrayList<>();
	private ListView sortList,listingList;
	private SortListAdapter sortListAdapter;
	private ListListAdapter listingListAdapter;
	
	private ImageButton backBtn,searchBtn;
	private Button settlementBtn;
	
	private LinearLayout frameLinea;
	private TextView tabMenu,tabPic,tabEva,totalPrice,disPrice,lable,titleTxt;
	
	private String restaurantName;
	private int resImg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		this.restaurantName = intent.getExtras().getString("RestauranName");
		this.resImg = intent.getExtras().getInt("ResImg");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.restaurant);
		
		initResource();
		initSetting();
	}
	private void initResource() {
		sortList = (ListView) findViewById(R.id.restaurant_sort_list);
		listingList = (ListView) findViewById(R.id.restaurant_listing_list);
		
		backBtn = (ImageButton) findViewById(R.id.title2_back_btn);
		searchBtn = (ImageButton) findViewById(R.id.title2_search_btn);
		settlementBtn = (Button) findViewById(R.id.restaurant_settlement_btn);
		
		tabMenu = (TextView)findViewById(R.id.restaurant_tab_menu);
		tabPic = (TextView)findViewById(R.id.restaurant_tab_pic);
		tabEva = (TextView)findViewById(R.id.restaurant_tab_eva);
		totalPrice = (TextView)findViewById(R.id.restaurant_totalprice_txt);
		disPrice = (TextView)findViewById(R.id.restaurant_disprice_txt);
		lable = (TextView)findViewById(R.id.restaurant_lable);
		
		frameLinea = (LinearLayout) findViewById(R.id.restaurant_frame_linea);

		titleTxt = (TextView) findViewById(R.id.title2_text);
		
		for (int i = 0; i < 7; i++) {
			ArrayList<Dishes> dishes = new ArrayList<>();
			for(int j = 0; j < 5; j++){
				Dishes di = new Dishes();
				di.setName(DISHES_NAME[i][j]);
				di.setLable(DISHES_LABLE[i][j]);
				di.setPrice(DISHES_PRICE[i][j]);
				di.setSaleNumber(DISHES_SALENUM[i][j]);
				di.setImg(DISHES_IMG[i][j]);
				dishes.add(di);
			}
			list.add(dishes);
		}

		sortListAdapter = new SortListAdapter(SORT_DATA);
		initAdapter(0);
	}  
	
	private void initSetting() {
		
		titleTxt.setText(restaurantName);
		
		backBtn.setVisibility(View.VISIBLE);
		backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RestaurantActivity.this.finish();
				
			}
		});
		
		settlementBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
//				new AlertDialog.Builder(RestaurantActivity.this)
//				.setTitle("��ʾ")
//				.setMessage("��ѡ��Ʒ�Ѽ����ҵķ�Ʊ")
//				.setPositiveButton("�õ�", null)
//				.show();
				
				Intent intent = new Intent(action);
				intent.putExtra("RestaurantName", restaurantName);
				intent.putExtra("ResImg", resImg);
				intent.putExtra("price", totalPrice.getText().toString());
				sendBroadcast(intent);
				//Toast.makeText(RestaurantActivity.this, "�Ѿ����͹㲥��", 0).show();
				finish();
				

				
			}
		});
		
		
		sortList.setAdapter(sortListAdapter);
		sortList.setDivider(null);
		
		sortList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				initAdapter(position);
				
			}
		});
		

		
	}
	
	void initAdapter(int position){
		listingListAdapter = new ListListAdapter(list.get(position));
		listingList.setAdapter(listingListAdapter);
		listingListAdapter.notifyDataSetChanged();
	}

	class SortListAdapter extends BaseAdapter{

		private String[] str;
		public SortListAdapter(String[] str) {
			this.str = str;
		}
		@Override
		public int getCount() {
			return str.length-1;
		}

		@Override
		public Object getItem(int position) {
			return str[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tx;
			if(convertView == null){
				LayoutInflater inflater = LayoutInflater.from(RestaurantActivity.this);
				convertView = inflater.inflate(R.layout.restaurant_list_sort, null);
				tx = (TextView) convertView.findViewById(R.id.restaurant_list_sort_tx);
				convertView.setTag(tx);
			}else{
				tx = (TextView) convertView.getTag();
			}
			
				tx.setText(str[position]);
				
			return convertView;
		}
		
	}
	
	class ListListAdapter extends BaseAdapter{

		private ArrayList<Dishes> listArr;
		public ListListAdapter(ArrayList<Dishes> list ) {
			this.listArr = list;
		}
		
		@Override
		public int getCount() {
			return listArr.size();
		}

		@Override
		public Object getItem(int position) {
			return listArr.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			 Holder holder = null;
			if(convertView == null){
				convertView = View.inflate(RestaurantActivity.this,R.layout.restaurant_list_linear, null);
				holder = new Holder();
				holder.name = (TextView) convertView.findViewById(R.id.restaurant_list_linear_name);
				holder.lable = (TextView) convertView.findViewById(R.id.restaurant_list_linear_lable);
				holder.price = (TextView) convertView.findViewById(R.id.restaurant_list_linear_price);
				holder.saleNumber = (TextView) convertView.findViewById(R.id.restaurant_list_linear_salenumber);
				holder.number = (TextView) convertView.findViewById(R.id.restaurant_list_linear_number);
				holder.imBtnAdd =  (ImageButton) convertView.findViewById(R.id.restaurant_list_linear_add);
				holder.imBtnSub =  (ImageButton) convertView.findViewById(R.id.restaurant_list_linear_sub);
				holder.img = (ImageView) convertView.findViewById(R.id.restautant_list_linear_picture);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			holder.name.setText(listArr.get(position).getName());
			holder.lable.setText(listArr.get(position).getLable());
			holder.price.setText(listArr.get(position).getPrice());
			holder.saleNumber.setText(listArr.get(position).getSaleNumber());
			//Drawable.createFromPath(listArr.get(position).getImg())
//			Drawable drawable = getDrawable(R.drawable.)
			holder.img.setImageResource(listArr.get(position).getImg());
			holder.imBtnAdd.setOnClickListener(new addListener(holder));
			holder.imBtnSub.setOnClickListener(new subListener(holder));
			return convertView;
		}

	}
	
	private class addListener implements OnClickListener{
		Holder holder;
		public addListener(Holder holder){
			this.holder = holder;
		}
		@Override
		public void onClick(View v) {
			holder.imBtnSub.setVisibility(View.VISIBLE);
			int num1 = 0;
			num1 = Integer.valueOf(holder.number.getText().toString()).intValue()  + 1;
			holder.number.setText(num1 + "");
			int num2  = Integer.valueOf(totalPrice.getText().toString()).intValue();
			int num3 = Integer.valueOf(holder.price.getText().toString()).intValue();
			totalPrice.setText(num2 + num3 + "");
			if(num2 + num3 >= 0){
				settlementBtn.setVisibility(View.VISIBLE);
				frameLinea.setVisibility(View.GONE);
			}
			
			
		}
		
	} 
	
	private class subListener implements OnClickListener{

		Holder holder;
		public subListener(Holder holder){
			this.holder = holder;
		}
		@Override
		public void onClick(View v) {
			if(holder.imBtnSub.getVisibility() == View.VISIBLE){
				 int num = 1;
				 num = Integer.valueOf(holder.number.getText().toString()).intValue()  - 1;
				 holder.number.setText(num + "");
				 if(num == 0){
					 holder.imBtnSub.setVisibility(View.GONE);
				 }
				 
				int num2  = Integer.valueOf(totalPrice.getText().toString()).intValue();
				int num3 = Integer.valueOf(holder.price.getText().toString()).intValue();
				totalPrice.setText(num2 - num3 + "");
				if(num2 - num3 <= 0){
					settlementBtn.setVisibility(View.GONE);
					frameLinea.setVisibility(View.VISIBLE);
				}
				
			}
		}
		
	}

	public final  class Holder{
		ImageButton imBtnAdd;
		ImageButton imBtnSub;
		ImageView img;
		TextView name;
		TextView lable;
		TextView saleNumber;
		TextView price;
		TextView number;
	}
	

}
