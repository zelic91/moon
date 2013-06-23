package com.zelic.moon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SubchapterView extends View {
	
	int chapterIndex=1;
	int subChapterIndex = 0;
	int textSize = 40;
	
	Paint foregroundPaint = new Paint();
	Paint strokePaint = new Paint();
	Paint textPaint = new Paint();
	Paint textOutlinePaint = new Paint();
	Path foregroundPath = new Path();

	
	public SubchapterView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initPaint();
		// TODO Auto-generated constructor stub
	}

	public SubchapterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPaint();
		// TODO Auto-generated constructor stub
	}

	public SubchapterView(Context context) {
		super(context);
		initPaint();
		// TODO Auto-generated constructor stub
	}
	
	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}
	

	public void setChapterIndex(int chapterIndex) {
		this.chapterIndex = chapterIndex;
	}

	public void setSubChapterIndex(int subChapterIndex) {
		this.subChapterIndex = subChapterIndex;
	}

	protected void initPaint(){
		
		foregroundPaint.setAntiAlias(true);
		foregroundPaint.setColor(Color.WHITE);
		foregroundPaint.setStyle(Style.FILL);
		
		
		strokePaint.setAntiAlias(true);
		strokePaint.setColor(Color.BLACK);
		strokePaint.setStrokeWidth(2);
		strokePaint.setStyle(Style.STROKE);
		
		textPaint.setColor(Color.WHITE);
		textPaint.setShadowLayer(2, 0, 0, Color.BLACK);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(textSize);
		
		textOutlinePaint.setTextAlign(Align.CENTER);
		textOutlinePaint.setAntiAlias(true);
		textOutlinePaint.setTextSize(textSize);
		textOutlinePaint.setColor(Color.BLACK);
		textOutlinePaint.setStyle(Style.STROKE);
		textOutlinePaint.setStrokeWidth(2);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		foregroundPath.lineTo(width, 0);
		foregroundPath.lineTo(width, height);
		foregroundPath.lineTo(0, height);
		foregroundPath.lineTo(0, 0);
		
		canvas.drawPath(foregroundPath, foregroundPaint);
		canvas.drawPath(foregroundPath, strokePaint);
		canvas.drawText(String.format("%d'%d", chapterIndex, subChapterIndex), canvas.getWidth()/2, 
				canvas.getHeight()/2-(textPaint.descent()+textPaint.ascent())/2, textOutlinePaint);
		canvas.drawText(String.format("%d'%d", chapterIndex, subChapterIndex), canvas.getWidth()/2, 
				canvas.getHeight()/2-(textPaint.descent()+textPaint.ascent())/2, textPaint);
		super.onDraw(canvas);
		
	}

}
