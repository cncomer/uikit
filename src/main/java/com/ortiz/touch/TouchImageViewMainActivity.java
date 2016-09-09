package com.ortiz.touch;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.cncom.app.common.uikit.R;


public class TouchImageViewMainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_image_view_main);
        findViewById(R.id.single_touchimageview_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(TouchImageViewMainActivity.this, SingleTouchImageViewActivity.class));
			}
		});
        findViewById(R.id.viewpager_example_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(TouchImageViewMainActivity.this, ViewPagerExampleActivity.class));
			}
		});
        findViewById(R.id.mirror_touchimageview_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(TouchImageViewMainActivity.this, MirroringExampleActivity.class));
			}
		});
        findViewById(R.id.switch_image_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(TouchImageViewMainActivity.this, SwitchImageExampleActivity.class));
			}
		});
        findViewById(R.id.switch_scaletype_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(TouchImageViewMainActivity.this, SwitchScaleTypeExampleActivity.class));
			}
		});
    }
}