package com.universlsoftware.nakathpathraya.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.universlsoftware.nakathpathraya.R;

import com.universlsoftware.nakathpathraya.bean.Post;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Common {
	public static String APP_MAIN_COLOR="#E43326";
	public static String APP_LINK="https://play.google.com/store/apps/details?id=com.universl.palapalasinhalen&hl=en";
	
	public static List<Post> resultList;
	
	
	// Keep all Images in array
	public final static Integer[] symbols = {
			      R.drawable.item_mesha,
			      R.drawable.item_vrushabha,
			      R.drawable.item_mithuna,
			      R.drawable.item_kataka,
			      R.drawable.item_sinha,
			      R.drawable.item_kanya,
			      R.drawable.item_thula,
			      R.drawable.item_vrushrika,
			      R.drawable.item_dhanu,
			      R.drawable.item_makara,
			      R.drawable.item_kumba,
			      R.drawable.item_meena,
	      
	   };
	
	public static boolean dataEnabled(Context context, boolean enabled) {
		try {
			final ConnectivityManager conman = (ConnectivityManager) context
					.getApplicationContext().getSystemService(
							Context.CONNECTIVITY_SERVICE);
			final Class conmanClass = Class
					.forName(conman.getClass().getName());
			final Field iConnectivityManagerField = conmanClass
					.getDeclaredField("mService");
			iConnectivityManagerField.setAccessible(true);
			final Object iConnectivityManager = iConnectivityManagerField
					.get(conman);
			final Class iConnectivityManagerClass = Class
					.forName(iConnectivityManager.getClass().getName());
			final Method setMobileDataEnabledMethod = iConnectivityManagerClass
					.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
			setMobileDataEnabledMethod.setAccessible(true);
			setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static  boolean checkNetwork(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		} else {
			return false;
		}
	}

	
}
