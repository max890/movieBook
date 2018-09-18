package com.powercode.test.max.moviebook.app.di.modules;

import android.content.Context;

import com.powercode.test.max.moviebook.R;
import com.powercode.test.max.moviebook.app.di.qualifiers.AppContext;
import com.powercode.test.max.moviebook.model.api.SearchMovieApi;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    static Retrofit provideRetrofit(@AppContext final Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url().newBuilder()
                                .addQueryParameter("apikey", context.getString(R.string.api_key))
                                .build();
                        Request newRequest = request.newBuilder()
                                .url(url)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_url))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }


    @Provides
    @Singleton
    static SearchMovieApi provideSearchMovieApi(final Retrofit retrofit) {
        return retrofit.create(SearchMovieApi.class);
    }
}
