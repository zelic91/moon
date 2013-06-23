package com.zelic.moon;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public class ScrollableWebView extends WebView implements
		OnScrollListener, OnScrollChangedListener {
	
	Handler handler;

	public ScrollableWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ScrollableWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ScrollableWebView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		int current=0;
		Message msg = new Message();
		
		switch (scrollState) {
		case OnScrollListener.SCROLL_STATE_FLING:
			 current = (int)((float)getScrollY()/(getContentHeight()*getScaleY()));
			break;
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			current = (int)((float)getScrollY()/(getContentHeight()*getScaleY()));
			break;
		default:
			break;
		}
		msg.arg1 = current;
		handler.sendMessage(msg);
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
		
	}

	@Override
	public void onScrollChanged() {
		int current=0;
		Message msg = new Message();
		current = (int)((float)getScrollY()/(getContentHeight()*getScaleY()));
		msg.arg1 = current;
		handler.sendMessage(msg);
		Log.e("MSG", "Send_2");
	}
	
	

}
