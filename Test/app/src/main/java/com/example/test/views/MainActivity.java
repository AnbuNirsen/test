package com.example.test.views;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.adapters.RecyclerAdapter;
import com.example.test.contracts.IMainContract;
import com.example.test.model.TestJsonResponse;
import com.example.test.network.RetrofitInterface;
import com.example.test.presenters.MainPresenter;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Response;
import rx.Observable;

public class MainActivity extends AppCompatActivity implements IMainContract.view{
private RecyclerView recyclerView;
private ProgressDialog progressDialog;
@Inject
public MainPresenter mainPresenter;
private RecyclerAdapter recyclerAdapter;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DaggerMainComponents.builder().mainPresenterModule(new MainPresenterModule(this)).build().inject(this);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        showDialog();
        Realm.init(this);
        mainPresenter.loadDatas();
    }


    @Override
    public void showDialog() {
        if(progressDialog!=null){
            progressDialog.show();
        }else{
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }
    }

    @Override
    public void hideDialog() {
        if(progressDialog!=null){
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }

    @Override
    public void onDataFetched(TestJsonResponse testJsonResponses) {
        hideDialog();

        recyclerAdapter = new RecyclerAdapter(testJsonResponses.getFacilities());
        Log.d(TAG, "onDataFetched: "+testJsonResponses.getFacilities().get(0).getName());
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public void onError(String msg) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
