#include <jni.h>
#include <android/log.h>
namespace com_marakana_android_fibonaccinative {
     static jlong fib(jlong n) {
	    return n <= 0 ? 0 : n == 1 ? 1 : fib(n - 1) + fib(n - 2);
  }
	 static jlong fibNR(JNIEnv *env, jclass clazz, jlong n) {
		__android_log_print(ANDROID_LOG_DEBUG, "FibLib.c",  "fibNR(%lld)",n);
		return fib(n);
	}
	static jlong fibNI(JNIEnv *env, jclass clazz, jlong n) {
		__android_log_print(ANDROID_LOG_DEBUG, "FibLib.c",  "fibNI(%lld)",n);
		jlong previous = -1;
		jlong result = 1;
		jlong i;
		for (i = 0; i < n; i++) {
			jlong sum = previous + result;
			previous = result;
			result = sum;
		}
		return result;
	}
	static JNINativeMethod method_table[] = {
			{"fibNR","(J)J", (void *) fibNR},
			{"fibNI","(J)J", (void *) fibNI}
	};
}
using namespace com_marakana_android_fibonaccinative ;

extern "C" jint JNI_OnLoad(JavaVM* vm, void* reserved){
    JNIEnv* env;
    if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }else{
    	jclass clazz = env->FindClass("com/marakana/android/fibonaccinative/FibLib");
    	if(clazz){
    		env->RegisterNatives(clazz,method_table,sizeof(method_table)/sizeof(method_table[0]));
    	    env->DeleteLocalRef(clazz);
    	    return JNI_VERSION_1_6;
    	}else{
    		return -1;
    	}
    }
}








