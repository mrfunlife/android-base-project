package mkgroup;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import java.util.concurrent.TimeUnit;


import combackandroid.mk.group.baseproject.BaseProject.API.BaseAPI;
import combackandroid.mk.group.baseproject.BaseProject.API.Utils.RxErrorHandlingCallAdapterFactory;
import combackandroid.mk.group.baseproject.R;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
//import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

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

        // initialize the singleton
        sInstance = this;
//
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder().baseUrl(BaseAPI.BASE_PATH)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .build();
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
