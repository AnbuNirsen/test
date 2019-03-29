package com.example.test.presenters;

import android.util.Log;

import com.example.test.contracts.IMainContract;
import com.example.test.model.Exclusion;
import com.example.test.model.Facility;
import com.example.test.model.JsonDb;
import com.example.test.model.TestJsonResponse;
import com.example.test.network.RetrofitClient;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.HttpException;
import retrofit2.Response;



public class MainPresenter extends BasePresenter<IMainContract.view> implements IMainContract.presenter {
    private IMainContract.view view;
    private List<TestJsonResponse> testJsonResponsesList;
    private static final String TAG = "MainPresenter";
    private Realm realm = Realm.getDefaultInstance();

    @Inject
    RetrofitClient retrofitClient;


    @Inject
    public MainPresenter(IMainContract.view view) {
        super(view);
        this.view = view;
    }

    @Override
    public void loadDatas() {
       addCompositeDisposable(retrofitClient.getApiInterface().getJsonResponse()
                              .observeOn(AndroidSchedulers.mainThread())
                              .subscribeOn(Schedulers.io())
                              .subscribe(new Consumer<Response<TestJsonResponse>>(){
                               @Override
                               public void accept(Response<TestJsonResponse> testJsonResponseResponse) throws Exception {
                                   Log.d(TAG, "accept: "+testJsonResponseResponse.body().getFacilities().get(0).getName());
                                   if(testJsonResponseResponse.isSuccessful()){
                                     if(testJsonResponseResponse.body()!=null){
//                                         view.onDataFetched(testJsonResponseResponse.body());
                                         loadIntoDb(testJsonResponseResponse.body());
                                     }
                                 }
                               }
                              },new Consumer<Throwable>(){
                              @Override
                              public void accept(Throwable throwable) throws Exception {
                                  handleThrowableError(throwable);
                              }
                              })
       );
    }

    public void loadIntoDb(TestJsonResponse testJsonResponse) {
        realm.beginTransaction();
        JsonDb jsonDb = new JsonDb();
        jsonDb.setFacilities((RealmList<Facility>) testJsonResponse.getFacilities());
        realm.copyFromRealm(jsonDb);
        realm.commitTransaction();
        retriveAll();
    }

    public void retriveAll(){
        RealmResults<TestJsonResponse> results = realm.where(TestJsonResponse.class).findAll();
        StringBuffer string = new StringBuffer();
        for(TestJsonResponse testJsonResponse:results){
            Log.e("result=>",testJsonResponse.getFacilities().get(0).getName());
        }
    }

    private void handleThrowableError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            view.onError(throwable.toString());
        } else if (throwable instanceof SocketTimeoutException) {
            view.onError(throwable.toString());
        } else if (throwable instanceof IOException) {
            view.onError(throwable.toString());
        } else {
            view.onError(throwable.toString());
        }
    }
}
