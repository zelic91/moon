package com.zelic.moon;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

public class MainActivity extends Activity {
	MoonView back;
	MoonView front;
	SubchapterView chapter;
	ScrollableWebView webView;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Log.d("MSG", "Receive");
			front.setCurrent(msg.arg1);
			front.invalidate();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		back = (MoonView)findViewById(R.id.back);
		front = (MoonView)findViewById(R.id.front);
		chapter = (SubchapterView)findViewById(R.id.chapter);
		webView = (ScrollableWebView)findViewById(R.id.webView);
		front.setDisplayPercentage(true);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ObjectAnimator anim = ObjectAnimator.ofFloat(front, "alpha", 0f, 1f);
				anim.setDuration(1000);
				ObjectAnimator anim02 = ObjectAnimator.ofFloat(front, "alpha", 1f, 0f);
				anim02.setStartDelay(4000);
				anim02.setDuration(1000);	
				AnimatorSet set = new AnimatorSet();
				set.play(anim).before(anim02);
				set.start();
			}
		});
		
		try {
			InputStream stream = getAssets().open("test.html");
			byte[] buffer = new byte[stream.available()];
			stream.read(buffer);
			String data = new String(buffer);
			webView.loadDataWithBaseURL("file:///android_asset", data, "text/html", "UTF-8", null);
			webView.setHandler(handler);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("OPEN HTML", "Fail", e);
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
