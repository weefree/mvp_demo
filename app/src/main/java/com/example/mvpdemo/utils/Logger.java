package com.example.mvpdemo.utils;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class Logger {
	
	public static final String TAG = "mvpdemo";
	
	public static void i(String msg){
		if(Config.D){
			Log.i(TAG,msg);
		}

	}
	
	public static void e(String msg){
		if(Config.D){
			Log.i(TAG,msg);

		}
	}
	
	public static void d(String msg){
		if(Config.D){
			Log.i(TAG, msg);
		}
	}
	public static void w(String msg){
		if(Config.D){
			Log.i(TAG,msg);
		}
	}

	public static void toast(Context context, int strRes){
		toast(context,context.getString(strRes));
	}
	
	public static void toast(final Context context, final String msg){
		new Handler(Looper.getMainLooper()).post(new Runnable() {
			
			@Override
			public void run() {
				if(context!=null&&msg!=null){
					Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public static void toastD(final Context context, final String msg){
		if(!Config.D)return;
		toast(context,msg);
		d(msg);
	}
}
