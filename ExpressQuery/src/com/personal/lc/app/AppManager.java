package com.personal.lc.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.personal.lc.bean.Company;
import com.personal.lc.net.HttpClient;
import com.personal.lc.net.NetApi;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;
import com.thinkland.sdk.android.SDKInitializer;

public class AppManager extends Application {

	public static AppManager appManager;

	/**
	 * 快递公司列表
	 */
	public static ArrayList<Company> companies = new ArrayList<Company>();

	/**
	 * 选中的快递公司编号
	 */
	public static String CNO = "";

	/**
	 * 订单编号
	 */
	public static String ONO = "";
	
	public static void initOrder(){
		CNO = "";
		ONO = "";
	}

	/**
	 * 全局intent
	 */
	public static Intent intent;

	@Override
	public void onCreate() {
		super.onCreate();

		initCompany("company.json");
		// for (Company company : companies) {
		// System.out.println(company.toString());
		// }
		SDKInitializer.initialize(getApplicationContext());
		appManager = this;
	}

	/**
	 * 初始化快递公司数据
	 * 
	 * @param fileName
	 */
	void initCompany(String fileName) {
		String jsonData = getFileContent(fileName);
		try {
			JSONObject jsonObj = new JSONObject(jsonData);
			JSONArray jsonArray = jsonObj.getJSONArray("company");

			Company company = null;
			for (int i = 0; i < jsonArray.length(); i++) {
				company = new Company();
				JSONObject childObj = jsonArray.getJSONObject(i);
				String cname = childObj.getString("com");
				String cno = childObj.getString("no");
				company.setmComName(cname);
				company.setmComNo(cno);
				companies.add(company);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 读取文件方法
	 * 
	 * @param fileName
	 * @return
	 */
	String getFileContent(String fileName) {
		StringBuffer companyJson = new StringBuffer("");
		InputStream is = null;
		try {
			is = getAssets().open(fileName);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bfr = new BufferedReader(isr);

			String tempString = "";
			while ((tempString = bfr.readLine()) != null) {
				companyJson.append(tempString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return companyJson.toString();
	}

	/**
	 * xUtils 请求函数 查询订单信息
	 * 
	 * @param requestCallBack
	 *            请求完成后的回调
	 */
	public static void queryOrderInfo(RequestCallBack<String> requestCallBack) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("com", CNO);
		params.addBodyParameter("no", ONO);
		params.addBodyParameter("key", NetApi.APP_KEY);

		HttpUtils http = HttpClient.getInstance();
		http.send(HttpMethod.POST, NetApi.ORDER_INFO_URL, params,
				requestCallBack);

	}
	/**
	 * 聚合API
	 * @param requestCallBack
	 */
	public static void requestJuhe(DataCallBack requestCallBack) {
		Parameters params = new Parameters();
		params.add("com", CNO);
		params.add("no", ONO);
		// params.add("key", NetApi.APP_KEY);
		JuheData.executeWithAPI(43, NetApi.ORDER_INFO_URL, JuheData.POST,
				params, requestCallBack);
	}

	/**
	 * 跳转另一个Activity
	 * 
	 * @param currPage
	 * @param targetPage
	 */
	public static void toOtherPage(Activity currPage, Class<?> targetPage) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(currPage, targetPage);
		currPage.startActivity(intent);
	}

	/**
	 * 跳转另一个Activity
	 * 
	 * @param currPage
	 * @param targetPage
	 * @param date
	 *            传到另一个Activity的数据
	 */
	public static void toOtherPage(Activity currPage, Class<?> targetPage,
			Bundle date) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.putExtras(date);
		intent.setClass(currPage, targetPage);
		currPage.startActivity(intent);
	}

	/**
	 * 网络连接是否正常
	 * 
	 * @return true 正常
	 */
	public static boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) appManager
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		return info != null && info.isConnectedOrConnecting();
	}
}
