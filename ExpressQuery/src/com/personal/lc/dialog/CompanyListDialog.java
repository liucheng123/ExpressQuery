package com.personal.lc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.personal.lc.activity.R;
import com.personal.lc.adapter.CompanyListAdapter;
import com.personal.lc.bean.Company;

public class CompanyListDialog extends Dialog {

	private Context context;

	private ListView mCompanyList;
	private CompanyListAdapter mCompanyListAdapter;

	public CompanyListDialog(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setCanceledOnTouchOutside(false);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.company_list_dialog);
		mCompanyList = (ListView) findViewById(R.id.company_listview);

		mCompanyListAdapter = new CompanyListAdapter(context);
		mCompanyList.setAdapter(mCompanyListAdapter);

		mCompanyList
				.setOnItemClickListener(new onCompanyListItemClickListener());
	}

	public interface onCompanyListItemClickListenerCallback {
		public void setSelectCompany(Company company);
	}

	private onCompanyListItemClickListenerCallback itemClickCallBack;

	public void setItemClickCallBack(
			onCompanyListItemClickListenerCallback itemClickCallBack) {
		this.itemClickCallBack = itemClickCallBack;
	}

	class onCompanyListItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Company compay = (Company) mCompanyListAdapter.getItem(position);
			if (itemClickCallBack != null) {
				itemClickCallBack.setSelectCompany(compay);
			}
			dismiss();
		}

	}

	@Override
	public void show() {
		super.show();
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
	}
}
