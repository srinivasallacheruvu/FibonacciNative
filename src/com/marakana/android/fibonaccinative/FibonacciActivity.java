package com.marakana.android.fibonaccinative;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FibonacciActivity extends Activity implements OnClickListener {
EditText input;
RadioGroup rg;
Button button;
TextView output;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fibonacci);
		input=(EditText) findViewById(R.id.editText1);
		rg=(RadioGroup) findViewById(R.id.type);
		rg.setOnClickListener(this);
		button=(Button) findViewById(R.id.button1);
		output=(TextView) findViewById(R.id.textView2);
		button.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		String s = this.input.getText().toString();
		if (TextUtils.isEmpty(s)) {
			return;
		}

		final ProgressDialog dialog = ProgressDialog.show(this, "",
				"Calculating...", true);
		final long n = Long.parseLong(s);
		new AsyncTask<Void, Void, String>() {

			@SuppressLint("DefaultLocale")
			@Override
			protected String doInBackground(Void... params) {
				long result = 0;
				long t = SystemClock.uptimeMillis();
				switch (FibonacciActivity.this.rg.getCheckedRadioButtonId()) {
				case R.id.FibJR:
					result = FibLib.fibJR(n);
					break;
				case R.id.FibJI:
					result = FibLib.fibJI(n);
					break;
				case R.id.FibNR:
					result = FibLib.fibNR(n);
					break;
				case R.id.FibNI:
					result = FibLib.fibNI(n);
					break;
				}
				t = SystemClock.uptimeMillis() - t;
				return String.format("fib(%d)=%d in %d ms", n, result, t);
			}

			@Override
			protected void onPostExecute(String result) {
				dialog.dismiss();
				FibonacciActivity.this.output.setText(result);
			}
		}.execute();
	}
}