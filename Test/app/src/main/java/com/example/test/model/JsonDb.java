package com.example.test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class JsonDb extends RealmObject{


private RealmList<Facility> facilities = null;

private List<RealmList<Exclusion>> exclusions = null;

    public RealmList<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(RealmList<Facility> facilities) {
        this.facilities = facilities;
    }

    public List<RealmList<Exclusion>> getExclusions() {
        return exclusions;
    }

    public void setExclusions(List<RealmList<Exclusion>> exclusions) {
        this.exclusions = exclusions;
    }
}