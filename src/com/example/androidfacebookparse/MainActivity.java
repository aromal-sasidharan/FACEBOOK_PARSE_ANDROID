package com.example.androidfacebookparse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

public class MainActivity extends Activity {
	static boolean isLogined = true;
	int requestCodeFB = 423553;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void loginFB(View v) {
		ParseFacebookUtils.logIn(MainActivity.this, requestCodeFB,
				new LogInCallback() {

					@Override
					public void done(ParseUser arg0, ParseException arg1) {
						// TODO Auto-generated method stub
						if (arg1 == null) {
							Log.e("TEST PARSE FB", "LOGOIN OK");
							// Log.e("TEST PARSE FB","USER NAME"+arg0.getUsername());

						} else {
							Log.e("TEST PARSE FB", "LOGOIN FAILED");
							arg1.printStackTrace();
						}

					}
				});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		
		
		/* The normal workflow for FB login is 
		 * 
		 * login-btn-pressed -> FB-login-window -> onActivityResult -> Login-Success.
		 * 
		 *  
		 *  the above flow some times causes crashes due to exception in the first login attempt in some devices say (SAMSUNG S DOUS 7562).
		 *  
		 *  and works flawlessly on the second attempt. 
		 *  
		 *  to solve the following code is enclosed in try catch.
		 *  
		 *  once the exception occurs we 		 *  
		 *    
		 *  try for a second login  and prevent looping using a boolean variable
		 */
		
		Log.e("TESTPARSE", "REQUEST CODE " + requestCode);
		try {
			if (requestCode == requestCodeFB) {
				//Session.getActiveSession().onActivityResult(this, requestCode,
					//	resultCode, data);
				ParseFacebookUtils.finishAuthentication(requestCode,
						resultCode, data);
				super.onActivityResult(requestCode, resultCode, data);
			}

		} catch (Exception e) {
			// TODO: handle exception

			
			//try for a second login  and prevent looping using a boolean variable
			if (requestCode == requestCodeFB && isLogined) {
				isLogined = false;
				MainActivity.this.loginFB(null);
			}
		}

	}
}
