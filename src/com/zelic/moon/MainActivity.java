package com.zelic.moon;

import android.os.Bundle;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.util.MonthDisplayHelper;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	MoonView back;
	MoonView front;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		back = (MoonView)findViewById(R.id.back);
		front = (MoonView)findViewById(R.id.front);
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
