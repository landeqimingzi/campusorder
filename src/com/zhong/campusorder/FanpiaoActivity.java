package com.zhong.campusorder;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FanpiaoActivity  extends Activity{

	private ListView fanpiaoList;
	private TextView textView;
	private ArrayList<Fanpiao> fanpiaos = new ArrayList<>();
	private fpListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fanpiao);
		
		IntentFilter filter = new IntentFilter(RestaurantActivity.action);
		registerReceiver(rec, filter);
		
		initResouce();
		initSettings();
	}
	
	BroadcastReceiver rec = new BroadcastReceiver(){
		

		@Override
		public void onReceive(Context context, Intent intent) {

			
			textView.setText("ÓÐ¶©·¹²úÉú");

			Fanpiao fanpiao = new Fanpiao();
			fanpiao.restaurantName = intent.getExtras().getString("RestaurantName");
			fanpiao.price = intent.getExtras().getString("price");	
			fanpiao.img = intent.getExtras().getInt("ResImg");
			fanpiaos.add(fanpiao);
			Toast.makeText(FanpiaoActivity.this, "shoudao" + fanpiaos.get(0).restaurantName, 0).show();
			initAdapter();
			adapter.notifyDataSetChanged();
			
			
		}
	};

	
	private void initSettings() {

	}


	
	private void initResouce() {
		textView = (TextView) findViewById(R.id.fanpiao_noDtata);
		fanpiaoList = (ListView) findViewById(R.id.fanpiao_list);

	}

	private void initAdapter() {
		textView.setVisibility(View.GONE);
		adapter = new fpListAdapter(fanpiaos);
		fanpiaoList.setAdapter(adapter);
	}
	


	private class fpListAdapter extends BaseAdapter{
		private ArrayList<Fanpiao> fps;
		public fpListAdapter(ArrayList<Fanpiao> fanpiaos){
			this.fps = fanpiaos;
		}
		@Override
		public int getCount() {
			return fps.size();
		}

		@Override
		public Object getItem(int position) {
			return fps.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder = null;
			if(convertView == null){
				convertView = View.inflate(FanpiaoActivity.this, R.layout.fanpiao_list, null);
				holder = new Holder();
				holder.imageView = (ImageView) convertView.findViewById(R.id.fanpiao_list_imageView);
				holder.price = (TextView) convertView.findViewById(R.id.fanpiao_list_price);
				holder.resName = (TextView) convertView.findViewById(R.id.fanpiao_list_resName);
				
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			holder.price.setText(fps.get(position).price);
			holder.resName.setText(fps.get(position).restaurantName);
			holder.imageView.setImageResource(fps.get(position).img);
			return convertView;
		}
		
	}
	private class Holder{
		ImageView imageView;
		TextView resName,price;
	}
	private class Fanpiao{
		public String restaurantName,price;
		public int img;
		public Fanpiao(){
			
		}

		public Fanpiao(String restaurantName,String price,int img){
			this.restaurantName = restaurantName;
			this.price = price;
			this.img = img;
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(rec);
	}

}
