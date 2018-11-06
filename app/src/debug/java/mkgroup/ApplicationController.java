package mkgroup;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.multidex.MultiDex;
import android.util.Base64;
import android.util.Log;

//import com.facebook.FacebookSdk;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.squareup.leakcanary.LeakCanary;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import combackandroid.mk.group.baseproject.BaseProject.API.BaseAPI;
import combackandroid.mk.group.baseproject.BaseProject.API.Utils.RxErrorHandlingCallAdapterFactory;

import combackandroid.mk.group.baseproject.R;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by hunghd on 4/18/17.
 */

public class ApplicationController extends Application {

    private static ApplicationController sInstance;

    private Retrofit mRetrofit;
    private OkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }

        LeakCanary.install(this);

        // initialize the singleton
        sInstance = this;
//        FacebookSdk.sdkInitialize(getApplicationContext());
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        ButterKnife.setDebug(true);
        Stetho.initializeWithDefaults(this);

        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder().baseUrl(BaseAPI.BASE_PATH)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .build();
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "mkgroup",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    public static synchronized ApplicationController getInstance() {
        return sInstance;
    }

    public synchronized <T> T getRetrofitService(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

//    public void changeServer(){
//        if(client == null){
//            return;
//        }
//        BaseAPI.changeBasePath();
//        mRetrofit = new Retrofit.Builder().baseUrl(BaseAPI.BASE_PATH)
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
//                .build();
//    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
}
