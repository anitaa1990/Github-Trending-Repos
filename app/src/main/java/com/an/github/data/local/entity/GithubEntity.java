package com.an.github.data.local.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.an.github.data.local.converter.TimestampConverter;
import com.google.gson.annotations.SerializedName;

@Entity
public class GithubEntity implements Parcelable {

    @NonNull
    @PrimaryKey
    private Long id;

    private Long page;

    private Long totalPages;

    private String name;

    @SerializedName("full_name")
    private String fullName;

    @Embedded
    private Owner owner;

    @SerializedName("html_url")
    private String htmlUrl;

    private String description;

    @SerializedName("contributors_url")
    private String contributorsUrl;

    @TypeConverters(TimestampConverter.class)
    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("stargazers_count")
    private Long starsCount;

    private Long watchers;
    private Long forks;
    private String language;

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public void setContributorsUrl(String contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(Long starsCount) {
        this.starsCount = starsCount;
    }

    public Long getWatchers() {
        return watchers;
    }

    public void setWatchers(Long watchers) {
        this.watchers = watchers;
    }

    public Long getForks() {
        return forks;
    }

    public void setForks(Long forks) {
        this.forks = forks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isLastPage() {
        return getPage() >= getTotalPages();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.page);
        dest.writeValue(this.totalPages);
        dest.writeString(this.name);
        dest.writeString(this.fullName);
        dest.writeParcelable(this.owner, flags);
        dest.writeString(this.htmlUrl);
        dest.writeString(this.description);
        dest.writeString(this.contributorsUrl);
        dest.writeString(this.createdAt);
        dest.writeValue(this.starsCount);
        dest.writeValue(this.watchers);
        dest.writeValue(this.forks);
        dest.writeString(this.language);
    }

    public GithubEntity() {
    }

    protected GithubEntity(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.page = (Long) in.readValue(Long.class.getClassLoader());
        this.totalPages = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.fullName = in.readString();
        this.owner = in.readParcelable(Owner.class.getClassLoader());
        this.htmlUrl = in.readString();
        this.description = in.readString();
        this.contributorsUrl = in.readString();
        this.createdAt = in.readString();
        this.starsCount = (Long) in.readValue(Long.class.getClassLoader());
        this.watchers = (Long) in.readValue(Long.class.getClassLoader());
        this.forks = (Long) in.readValue(Long.class.getClassLoader());
        this.language = in.readString();
    }

    public static final Creator<GithubEntity> CREATOR = new Creator<GithubEntity>() {
        @Override
        public GithubEntity createFromParcel(Parcel source) {
            return new GithubEntity(source);
        }

        @Override
        public GithubEntity[] newArray(int size) {
            return new GithubEntity[size];
        }
    };
}
