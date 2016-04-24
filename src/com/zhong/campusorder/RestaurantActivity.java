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
	private static final String[] SORT_DATA = {"今日推荐","特价菜系","素菜","荤菜","套餐系列","销量排行","折扣商品","不知道"};
	private static final String[][] DISHES_NAME = {
		{"肥牛锅仔（微辣、麻辣）","腰果炒肉（养身健脾）","韩式猪排饭","鱼香肉丝饭","蜜汁排骨"},
		{"小炒拆骨肉","土豆红烧肉","椒盐蘑菇","鲍汁狮子头","香辣鸭脯"},
		{"蚝油土豆","红烧茄子","酸辣土豆丝","蒜香金针菇","日本豆腐"},
		{"咖喱牛肉","香菇滑鸡","广式叉烧","香辣鱼块","土豆盐煎肉"},
		{"小份黄焖鸡+2份神秘配菜","手撕包菜盖饭+百事可乐","红烧狮子头+小菜","鱼香肉丝盖饭+心情饮品","大盘鸡双人套餐"},
		{"糖醋小丸子","古法狮子头","干锅鱿鱼丝","农家小炒肉","泡菜肉丝"},
		{"绿色毛肚","金针菇拌豆芽","油炸花生米","尖椒变蛋","八宝菠菜"},
	};
	private static final String[][] DISHES_LABLE = {
		{"五星推荐","五星推荐","五星推荐","五星推荐","五星推荐"},
		{"五星推荐","红评如潮","店长推荐","特色菜","五星推荐"},
		{"店长推荐","红评如潮","五星推荐","五星推荐","五星推荐"},
		{"特色菜","特色菜","店长推荐","五星推荐","五星推荐"},
		{"超值套餐","超值套餐","超值套餐","超值套餐","超值套餐"},
		{"五星推荐","红评如潮","特色菜","超值套餐","店长推荐"},
		{"超低折扣","超低折扣","超低折扣","超低折扣","超低折扣"}
		
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
		{"月售163份","月售203份","月售258份","月售234份","月售221份"},
		{"月售175份","月售253份","月售238份","月售259份","月售131份"},
		{"月售103份","月售182份","月售154份","月售161份","月售218份"},
		{"月售256份","月售65份","月售654份","月售23份","月售123份"},
		{"月售115份","月售206份","月售94份","月售15份","月售45份"},
		{"月售356份","月售342份","月售394份","月售527份","月售445份"},
		{"月售56份","月售35份","月售65份","月售20份","月售18份"}
		
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
//				.setTitle("提示")
//				.setMessage("所选菜品已加入我的饭票")
//				.setPositiveButton("好的", null)
//				.show();
				
				Intent intent = new Intent(action);
				intent.putExtra("RestaurantName", restaurantName);
				intent.putExtra("ResImg", resImg);
				intent.putExtra("price", totalPrice.getText().toString());
				sendBroadcast(intent);
				//Toast.makeText(RestaurantActivity.this, "已经发送广播了", 0).show();
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
