package com.example.androidfacebookparse;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

public class MyApplication extends Application {
	
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		Parse.initialize(this, "ig8ZhQG1cnzUFDWOJqfJfcQqsxG1uNu5wSuEt2OO",
		"yErLAuwS6Zrs43XihNLwnqHl1wl5VuWQepYHJ4NM");
		ParseFacebookUtils.initialize("551581004928693");
	}

}
