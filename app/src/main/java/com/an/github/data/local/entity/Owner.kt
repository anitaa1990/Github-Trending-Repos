package com.an.github.data.local.entity

import android.arch.persistence.room.Entity
import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

@Entity
class Owner(var login: String,

            @SerializedName("avatar_url")
            var avatarUrl: String) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(login)
        writeString(avatarUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Owner> = object : Parcelable.Creator<Owner> {
            override fun createFromParcel(source: Parcel): Owner = Owner(source)
            override fun newArray(size: Int): Array<Owner?> = arrayOfNulls(size)
        }
    }
}
