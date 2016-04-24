package com.zhong.campusorder;

import java.util.Timer;
import java.util.TimerTask;

import com.zhong.campusorder.ui.TabShow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class CampusOrderActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				Intent intent = new Intent(CampusOrderActivity.this, TabShow.class);
				startActivity(intent);
				
			}
		}, 2000);
	}
	
}
