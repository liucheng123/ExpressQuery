package com.personal.lc.dialog;

import android.app.ProgressDialog;
import android.content.Context;

import com.personal.lc.activity.R;

public class LoadingDialog {
	private static ProgressDialog dialog;

	private static void initDialog(Context context) {
		dialog = new ProgressDialog(context);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		dialog.setContentView(R.layout.loading_dialog);
	}

	public static void showDialog(Context context, String title, String msg) {
		if (dialog == null) {
			initDialog(context);
		} else {
			dialog.setTitle(title);
			dialog.setMessage(msg);
			dialog.show();
		}

	}

	public static void showDialog(Context context) {
		if (dialog == null) {
			initDialog(context);
		} else {
			dialog.setTitle("提示");
			dialog.show();
		}

	}

	public static void hideDialog() {
		if (dialog != null)
			dialog.dismiss();
		dialog = null;
	}
}
