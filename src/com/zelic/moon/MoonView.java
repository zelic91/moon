package com.zelic.moon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class MoonView extends View {

	int start = 0;
	int end = 100;
	int current = 80;
	int textSize = 40;
	int backgroundColor = Color.LTGRAY;
	int foregroundColor = Color.GRAY;
	int strokeColor = Color.BLACK;
	boolean displayPercentage = true;

	Paint backgroundPaint = new Paint();
	Paint foregroundPaint = new Paint();
	Paint strokePaint = new Paint();
	Paint textPaint = new Paint();
	Path backgroundPath = new Path();
	Path foregroundPath = new Path();

	public MoonView(Context context, AttributeSet attrs, int defStyle) {
		this(context);
		// TODO Auto-generated constructor stub
	}

	public MoonView(Context context, AttributeSet attrs) {
		this(context);
		// TODO Auto-generated constructor stub
	}

	public MoonView(Context context) {
		super(context);

		initPaint();
		// TODO Auto-generated constructor stub
	}

	public void setBounds(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setForegroundColor(int foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public void setPercent(int current) {
		this.current = current;
	}

	public void setDisplayPercentage(boolean displayPercentage) {
		this.displayPercentage = displayPercentage;
	}

	public void initPaint() {
		backgroundPaint.setAntiAlias(true);
		backgroundPaint.setColor(backgroundColor);

		foregroundPaint.setAntiAlias(true);
		foregroundPaint.setColor(foregroundColor);

		strokePaint.setAntiAlias(true);
		strokePaint.setStyle(Style.STROKE);
		strokePaint.setColor(strokeColor);
		strokePaint.setStrokeJoin(Join.ROUND);
		strokePaint.setStrokeCap(Cap.ROUND);
		strokePaint.setStrokeWidth(1);
		
		textPaint.setColor(Color.WHITE);
		textPaint.setFakeBoldText(true);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(textSize);
		textPaint.setShadowLayer(1.5f, 0, 0, Color.BLACK);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		int amount = (int)(width*((float)current/(end-start)));

		// Draw the background
		backgroundPath.moveTo(width / 2, 0);

		// Half right part of the Moon
		backgroundPath.quadTo(width, 0, width, height / 2);
		backgroundPath.quadTo(width, height, width / 2, height);

		// Half left part of the Moon
		backgroundPath.quadTo(0, height, 0, height / 2);
		backgroundPath.quadTo(0, 0, width / 2, 0);

		canvas.drawPath(backgroundPath, backgroundPaint);
		canvas.drawPath(backgroundPath, strokePaint);

		//Calculate to percent part
		
		foregroundPath.moveTo(width/2, 0);
		// Half right part of the Moon
		foregroundPath.quadTo(width, 0, width, height / 2);
		foregroundPath.quadTo(width, height, width / 2, height);

		// Half left part of the Moon
		foregroundPath.quadTo(amount, height, amount, height / 2);
		foregroundPath.quadTo(amount, 0, width / 2, 0);
		
		canvas.drawPath(foregroundPath, foregroundPaint);
		canvas.drawPath(foregroundPath, strokePaint);
		
		if (displayPercentage) {
			canvas.drawText(""+current, width/2, height/2-(textPaint.descent()+textPaint.ascent())/2, textPaint);
		}

		super.onDraw(canvas);
	}

}
