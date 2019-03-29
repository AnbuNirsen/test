package com.example.test.contracts;

import com.example.test.model.TestJsonResponse;

import java.util.List;

public interface IMainContract {
    interface view{
        void showDialog();
        void hideDialog();
        void onDataFetched(TestJsonResponse testJsonResponses);
        void onError(String msg);

    }
    interface presenter {
        void loadDatas();
    }
}
