package com.an.github.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.an.github.data.local.entity.GithubEntity;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GithubApiResponse implements Parcelable {

    public GithubApiResponse() {
        this.items = new ArrayList<>();
    }

    @SerializedName("total_count")
    private Long totalCount;

    private List<GithubEntity> items;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<GithubEntity> getItems() {
        return items;
    }

    public void setItems(List<GithubEntity> items) {
        this.items = items;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.totalCount);
        dest.writeTypedList(this.items);
    }

    protected GithubApiResponse(Parcel in) {
        this.totalCount = (Long) in.readValue(Long.class.getClassLoader());
        this.items = in.createTypedArrayList(GithubEntity.CREATOR);
    }

    public static final Creator<GithubApiResponse> CREATOR = new Creator<GithubApiResponse>() {
        @Override
        public GithubApiResponse createFromParcel(Parcel source) {
            return new GithubApiResponse(source);
        }

        @Override
        public GithubApiResponse[] newArray(int size) {
            return new GithubApiResponse[size];
        }
    };
}
