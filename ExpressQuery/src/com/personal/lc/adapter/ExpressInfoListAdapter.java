package com.personal.lc.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.personal.lc.activity.R;
import com.personal.lc.bean.ResponseInfo.LogisticsInfo;
import com.personal.lc.widget.ViewHolder;

public class ExpressInfoListAdapter extends BaseAdapter {

	private List<LogisticsInfo> logisticsInfos;
	private LayoutInflater mLayoutInflater;
	private Context context;
	public ExpressInfoListAdapter(Context context,
			List<LogisticsInfo> logisticsInfos) {
		this.context = context;
		this.logisticsInfos = logisticsInfos;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return logisticsInfos.size();
	}

	@Override
	public Object getItem(int position) {
		return logisticsInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.express_info_item, null);
		}
		LogisticsInfo info = logisticsInfos.get(position);
		ImageView timeLine = ViewHolder.getView(convertView, R.id.time_line);
		if (position == 0) {
			timeLine.setImageDrawable(context.getResources().getDrawable(R.drawable.time_start));
		}else if(position == (getCount() - 1)){
			timeLine.setImageDrawable(context.getResources().getDrawable(R.drawable.time_end));
		}else{
			timeLine.setImageDrawable(context.getResources().getDrawable(R.drawable.time_normal));
		}
		
		TextView infoText = ViewHolder.getView(convertView, R.id.info_text);
		infoText.setText(info.getRemark());
		
		TextView timeText = ViewHolder.getView(convertView, R.id.time_text);
		timeText.setText(info.getDatetime());
		return convertView;
	}

}
