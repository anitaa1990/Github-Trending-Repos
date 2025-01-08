package com.an.github.data.local.entity

import com.google.gson.annotations.SerializedName

//@Entity
class Owner(var login: String,

            @SerializedName("avatar_url")
            var avatarUrl: String) {
}
