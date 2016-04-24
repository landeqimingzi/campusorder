package com.zhong.campusorder.ui;

import com.zhong.campusorder.FanpiaoActivity;
import com.zhong.campusorder.FindActivity;
import com.zhong.campusorder.MerchantsActivity;
import com.zhong.campusorder.MyActivity;
import com.zhong.campusorder.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class TabShow extends TabActivity implements OnCheckedChangeListener {
	private TabHost mHost;
	private RadioGroup tabItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tab_show);
		init();
	
	}

	private void init() {

			mHost = getTabHost();
			mHost.addTab(mHost.newTabSpec("MERCHANTS").setIndicator("MERCHANTS")
					.setContent(new Intent(this,MerchantsActivity.class)));
			mHost.addTab(mHost.newTabSpec("FANPIAO").setIndicator("FANPIAO")
					.setContent(new Intent(this,FanpiaoActivity.class)));
			mHost.addTab(mHost.newTabSpec("FIND").setIndicator("FIND")
					.setContent(new Intent(this,FindActivity.class)));
			mHost.addTab(mHost.newTabSpec("MY").setIndicator("MY")
					.setContent(new Intent(this,MyActivity.class)));

		
		tabItems = (RadioGroup) findViewById(R.id.tab_items);
		
		tabItems.setOnCheckedChangeListener(this);
		
		
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		switch (checkedId) {
			case R.id.tab_item_merchants:
				mHost.setCurrentTabByTag("MERCHANTS");
				break;
			case R.id.tab_item_fanpiao:
				mHost.setCurrentTabByTag("FANPIAO");
				break;
			case R.id.tab_item_find:
				mHost.setCurrentTabByTag("FIND");
				break;
			case R.id.tab_item_my:
				mHost.setCurrentTabByTag("MY");
				break;
			default:
				break;
		}
		
	}
}
