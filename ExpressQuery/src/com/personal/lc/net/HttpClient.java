package com.personal.lc.net;

import com.lidroid.xutils.HttpUtils;

public class HttpClient {

	private static HttpUtils httpUtils;
	private static final int TIME_OUT = 1000*60*2;

	public static HttpUtils getInstance(){
		if( httpUtils == null ){
			httpUtils = new HttpUtils();
			httpUtils.configTimeout(TIME_OUT);
			httpUtils.configSoTimeout(TIME_OUT);
		}
		return httpUtils;
	}
}
