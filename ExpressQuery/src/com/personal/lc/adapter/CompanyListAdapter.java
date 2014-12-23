package com.personal.lc.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.personal.lc.activity.R;
import com.personal.lc.app.AppManager;
import com.personal.lc.bean.Company;
import com.personal.lc.widget.ViewHolder;
/**
 * 快递公司列表Adapter
 * @author Administrator
 *
 */
public class CompanyListAdapter extends BaseAdapter {

	private List<Company> mCompanies = AppManager.companies;
	private LayoutInflater mLayoutInflater;

	private TextView cName;
	private TextView cNo;

	public CompanyListAdapter(Context context) {
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mCompanies.size();
	}

	@Override
	public Object getItem(int position) {
		return mCompanies.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.company_list_item,
					null);
		}

		cName = ViewHolder.getView(convertView, R.id.company_name);
		cNo = ViewHolder.getView(convertView, R.id.company_no);

		cName.setText(mCompanies.get(position).getmComName());
		cNo.setText(mCompanies.get(position).getmComNo());

		return convertView;
	}

}
