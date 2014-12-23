package com.personal.lc.bean;

/**
 * 快递公司对象
 * @author LC
 *
 */
public class Company {
	
	private String mComName;
	private String mComNo;

	public String getmComName() {
		return mComName;
	}

	public void setmComName(String mComName) {
		this.mComName = mComName;
	}

	public String getmComNo() {
		return mComNo;
	}

	public void setmComNo(String mComNo) {
		this.mComNo = mComNo;
	}
	@Override
	public String toString() {
		
		return mComNo + "_" +mComName;
	}
}
