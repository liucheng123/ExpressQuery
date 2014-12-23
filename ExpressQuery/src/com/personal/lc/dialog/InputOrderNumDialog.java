package com.personal.lc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.personal.lc.activity.R;

public class InputOrderNumDialog extends Dialog {


	private EditText mOrderNum;
	private Button mConfirmBtn;
	private Context context;
	public InputOrderNumDialog(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setCanceledOnTouchOutside(false);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.input_order_dialog);
		initView();
	}
	
	public interface onConfirmBtnClickCallback{
		public void setOrderNumber(String OrderNo);
	}
	private onConfirmBtnClickCallback onConfirmBtnClickCallback;
	
	
	public void setOnConfirmBtnClickCallback(
			onConfirmBtnClickCallback onConfirmBtnClickCallback) {
		this.onConfirmBtnClickCallback = onConfirmBtnClickCallback;
	}

	void initView(){
		mConfirmBtn = (Button) findViewById(R.id.confirm_btn);
		mOrderNum = (EditText) findViewById(R.id.order_num);
		
		mConfirmBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (onConfirmBtnClickCallback != null) {
					String orderNum = mOrderNum.getText().toString();
					if (TextUtils.isEmpty(orderNum)){
						Toast.makeText(context, context.getResources().getString(R.string.order_warning_tips), Toast.LENGTH_LONG).show();
						return;
					}
					onConfirmBtnClickCallback.setOrderNumber(orderNum);
				}
				dismiss();
			}
		});
	}
	
	


	@Override
	public void show() {
		super.show();
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
	}
}
