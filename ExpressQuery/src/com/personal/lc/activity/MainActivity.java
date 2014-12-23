package com.personal.lc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.personal.lc.app.AppManager;
import com.personal.lc.bean.Company;
import com.personal.lc.bean.ResponseInfo.Result;
import com.personal.lc.dialog.CompanyListDialog;
import com.personal.lc.dialog.CompanyListDialog.onCompanyListItemClickListenerCallback;
import com.personal.lc.dialog.InputOrderNumDialog;
import com.personal.lc.dialog.InputOrderNumDialog.onConfirmBtnClickCallback;
import com.personal.lc.dialog.LoadingDialog;
import com.personal.lc.dialog.MessageDialog;
import com.personal.lc.widget.LatticeItemView;
import com.thinkland.sdk.android.DataCallBack;

public class MainActivity extends Activity {

	@ViewInject(R.id.select_company_btn)
	private LatticeItemView mCompanySelector;

	@ViewInject(R.id.input_order)
	private LatticeItemView mInputOrder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ViewUtils.inject(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@OnClick(R.id.select_company_btn)
	void onSelectCompanyBtnClick(View view) {

		CompanyListDialog cld = new CompanyListDialog(this);
		cld.setItemClickCallBack(new onCompanyListItemClickListenerCallback() {

			@Override
			public void setSelectCompany(Company company) {
				AppManager.CNO = company.getmComNo();
				mCompanySelector.setText(company.getmComName());
			}
		});
		cld.show();
	}

	@OnClick(R.id.input_order)
	void onInputOrderBtnClick(View view) {
		InputOrderNumDialog ion = new InputOrderNumDialog(this);
		ion.setOnConfirmBtnClickCallback(new onConfirmBtnClickCallback() {
			@Override
			public void setOrderNumber(String OrderNo) {
				mInputOrder.setText(OrderNo);
				AppManager.ONO = OrderNo;
			}
		});
		ion.show();
	}

	@OnClick(R.id.query_btn)
	void onQueryBtnOnClick(View view) {
		if (TextUtils.isEmpty(AppManager.CNO) || TextUtils.isEmpty(AppManager.ONO)) {
			Toast.makeText(this,
					getResources().getString(R.string.text_info_null_tips),
					Toast.LENGTH_LONG).show();
			return;
		}
		if(!AppManager.isNetworkConnected()){
			new MessageDialog(MainActivity.this, "请检查您的网络是否可用！~").show();
			return;
		}
		
		LoadingDialog.showDialog(MainActivity.this);
		AppManager.requestJuhe(new DataCallBack() {
			@Override
			public void resultLoaded(int err, String reason, String result) {
				if (err == 0) {
					LoadingDialog.hideDialog();
					Gson gson = new Gson();
					com.personal.lc.bean.ResponseInfo responseObj = gson.fromJson(
							result,
							com.personal.lc.bean.ResponseInfo.class);

					if (!responseObj.getResultcode().equals("200") || responseObj.getError_code() != 0) {
						new MessageDialog(MainActivity.this, responseObj
								.getReason()).show();
						return;
					}
					Result resultObj = gson.fromJson(responseObj.getResult(),
							Result.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("result", resultObj);
					AppManager.toOtherPage(MainActivity.this, ExpressInfoActivity.class, bundle);
				}else{
					new MessageDialog(MainActivity.this, reason).show();
					LoadingDialog.hideDialog();
				}
			}
		});
		
	}
	/**
	 * xUtils 请求
	 */
	void sendRequest(){
		AppManager.queryOrderInfo(new RequestCallBack<String>() {
			@Override
			public void onStart() {
				LoadingDialog.showDialog(MainActivity.this);
			}
			
			@Override
			public void onSuccess(ResponseInfo<String> response) {
				LoadingDialog.hideDialog();
				Gson gson = new Gson();
				com.personal.lc.bean.ResponseInfo responseObj = gson.fromJson(
						response.result,
						com.personal.lc.bean.ResponseInfo.class);

				if (!responseObj.getResultcode().equals("200") || responseObj.getError_code() != 0) {
					new MessageDialog(MainActivity.this, responseObj
							.getReason() + "\r\n" + responseObj.getError_code() + "\r\n" + responseObj.getResultcode()).show();
					return;
				}
				Result result = gson.fromJson(responseObj.getResult(),
						Result.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("result", result);
				AppManager.toOtherPage(MainActivity.this, ExpressInfoActivity.class, bundle);
			}
			@Override
			public void onFailure(HttpException e, String msg) {
				new MessageDialog(MainActivity.this, e.getMessage() + "\r\n may be internet unconnect").show();
				LoadingDialog.hideDialog();
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		AppManager.initOrder();
		finish();
	}

}
