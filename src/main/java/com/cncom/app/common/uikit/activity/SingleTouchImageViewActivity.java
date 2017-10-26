package com.cncom.app.common.uikit.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.cncom.app.common.uikit.R;
import com.ortiz.touch.TouchImageView;


public class SingleTouchImageViewActivity extends AppCompatActivity {
	private static final String TAG = "SingleTouchImageViewActivity";
	public static final int DIALOG_PROGRESS = 10008;
	protected TouchImageView touchImageView;
	protected String uri;
	protected Context mContext;
	public static final String EXTRA_URI = "uri";

	@SuppressLint("LongLogTag")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.uikit_activity_single_touchimageview);
		touchImageView = (TouchImageView) findViewById(R.id.touchImageView);
		touchImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				performExitAnimation();
			}
		});
		String uri = getIntent().getStringExtra(EXTRA_URI);
		if (uri == null)  {
			Log.e(TAG, "onCreate uri == null, finish");
			finish();
			return;
		}

		if (uri.toString().startsWith("http")) {
			//远程的图片，我们需要下载缓存
			downloadAndShow(uri);
		} else {
			touchImageView.setImageURI(Uri.parse(uri));
		}
	}


	@Override
	public void onBackPressed() {
		performExitAnimation();
	}

	/***
	 * 下载并显示,之类实现
	 * @param uri
     */
	protected void downloadAndShow(String uri) {

	}


	@Override
	public Dialog onCreateDialog(int id) {
		switch(id) {
			case DIALOG_PROGRESS:
				ProgressDialog progressDialog = new ProgressDialog(this);
				progressDialog.setMessage(getString(R.string.msg_progressdialog_wait));
				progressDialog.setCancelable(false);
				return progressDialog;
		}
		return super.onCreateDialog(id);
	}

	public static final void startActivity(Context context, String uri) {
		Intent intent = new Intent();
		intent.setAction(context.getPackageName()+".intent.action.ViewImage");
		intent.putExtra(EXTRA_URI, uri);
		intent.setPackage(context.getPackageName());
		context.startActivity(intent);
	}

	protected void performExitAnimation() {
		finish();
	}
}
