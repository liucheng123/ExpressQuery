package com.personal.lc.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.personal.lc.activity.R;

public class LatticeItemView extends TextView {
	private boolean isSquare;
	public LatticeItemView(Context context) {
		this(context, null);
	}

	public LatticeItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.main_item);
		isSquare = attr.getBoolean(R.styleable.main_item_isSquare, false);
	}

	public LatticeItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = getMeasuredWidth();
		if (isSquare) {
			setMeasuredDimension(width, width);
		}else{
			setMeasuredDimension(width, width/2);
		}
	}
	
}
