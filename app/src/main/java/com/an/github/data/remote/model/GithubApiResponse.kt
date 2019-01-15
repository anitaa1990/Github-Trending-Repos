package com.an.github.data.remote.model

import android.os.Parcel
import android.os.Parcelable
import com.an.github.data.local.entity.GithubEntity
import com.google.gson.annotations.SerializedName


class GithubApiResponse(@SerializedName("total_count")
                        var totalCount: Long,

                        var items: List<GithubEntity>) : Parcelable {

    constructor(source: Parcel) : this(
            source.readLong(),
            source.createTypedArrayList(GithubEntity.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(totalCount)
        writeTypedList(items)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<GithubApiResponse> = object : Parcelable.Creator<GithubApiResponse> {
            override fun createFromParcel(source: Parcel): GithubApiResponse = GithubApiResponse(source)
            override fun newArray(size: Int): Array<GithubApiResponse?> = arrayOfNulls(size)
        }
    }
}
