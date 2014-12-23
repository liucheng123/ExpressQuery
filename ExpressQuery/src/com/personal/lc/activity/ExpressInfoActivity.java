package com.personal.lc.activity;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.personal.lc.adapter.ExpressInfoListAdapter;
import com.personal.lc.app.AppManager;
import com.personal.lc.bean.ResponseInfo.Result;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class ExpressInfoActivity extends Activity {
	
	private Result result;
	
	@ViewInject(R.id.express_title)
	private TextView mTitle;
	
	@ViewInject(R.id.express_list)
	private ListView mExpressInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_express_info);
		ViewUtils.inject(this);
		
		Intent intent = getIntent();
		result = (Result) intent.getExtras().get("result");
		initView();
	}
	
	void initView(){
		mTitle.setText(result.getCompany() + "	" + AppManager.ONO);
		mExpressInfo.setAdapter(new ExpressInfoListAdapter(this, result.getList()));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.express_info, menu);
		return true;
	}
}
