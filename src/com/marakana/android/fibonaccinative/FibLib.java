package com.marakana.android.fibonaccinative;

import android.util.Log;

public class FibLib {
	private static final String TAG="FibLib";
	static {
		System.loadLibrary("FibLib");
	}
public static long fibJR(long n){
	Log.d(TAG, "fibJR(" + n + ")");
	return n<=0 ? 0:n==1?1:fibJR(n-1)+fibJR(n-2);
	
}
public static native long fibNR(long n);

public static long fibJI(long n){
	Log.d(TAG, "fibJR(" + n + ")");
	long previous=-1;
	long result=1;
	for(long i=0;i<n;i++){
		long sum=previous+result;
		previous=result;
		result=sum;
	}
	return result;
}
public static native long fibNI(long n);

}
