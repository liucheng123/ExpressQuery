package com.personal.lc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.personal.lc.activity.R;

public class MessageDialog extends Dialog {
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private  String mTitle = "温馨提示";
	private  String mContent = "";
	private  String mOkBtnText = "确定";
	private  String mCancelBtnText = "取消";
	
	private TextView mTitleView,mContentView;
	private Button mOkBtnView,mCancelBtnView;
	
	/**
	 * 只有确定按钮，无回调
	 * @param context context
	 * @param content 显示的内容
	 */
	public MessageDialog(Context context, String content) {
		super(context);
		this.mContext = context;
		this.mContent = content;
		
		initView();
		mCancelBtnView.setVisibility(View.GONE);
		mOkBtnView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.msg_click_default));
		mOkBtnView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}
	
	/**
	 * 只有确定按钮，有回调
	 * @param context
	 * @param content
	 */
	public MessageDialog(Context context, String content, String okBtnStr) {
		super(context);
		this.mContext = context;
		this.mContent = content;
		this.mOkBtnText = okBtnStr;
		initView();
		mCancelBtnView.setVisibility(View.GONE);
		mOkBtnView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}
	
	public MessageDialog(Context context, String content, String okBtnStr, String cancelBtnStr, final Object callback) {
		super(context);
		this.mContext = context;
		this.mContent = content;
		this.mOkBtnText = okBtnStr;
		this.mCancelBtnText = cancelBtnStr;
		initView();
		
		mCancelBtnView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});
		mOkBtnView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});
	}
	

	
	void initView(){
		mLayoutInflater = LayoutInflater.from(mContext);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 一定要在 setContentView() 前调用
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		View dialogLayout = mLayoutInflater.inflate(R.layout.message_dialog, null);
		setContentView(dialogLayout);
		initWidget(dialogLayout);
		this.setCanceledOnTouchOutside(false);
	}
	
	private void initWidget(View dialogLayout){
		mTitleView = (TextView) dialogLayout.findViewById(R.id.tv_title);
		mContentView = (TextView) dialogLayout.findViewById(R.id.tv_content);
		mOkBtnView = (Button) dialogLayout.findViewById(R.id.button_set_later);
		mCancelBtnView = (Button) dialogLayout.findViewById(R.id.button_set_now);
		
		mTitleView.setText(mTitle);
		mContentView.setText(mContent);
		mOkBtnView.setText(mOkBtnText);
		mCancelBtnView.setText(mCancelBtnText);
	}
}
