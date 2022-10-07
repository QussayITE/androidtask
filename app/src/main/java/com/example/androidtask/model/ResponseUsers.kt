package com.example.androidtask.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseUsers() : Parcelable{

    @SerializedName("albumId")
    var albumId: Int = 0

    @SerializedName("userId")
    var userId: Int = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("url")
    var url: String = ""

    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String = ""

    constructor(parcel: Parcel) : this() {
        albumId = parcel.readInt()
        userId = parcel.readInt()
        name = parcel.readString()!!
        url = parcel.readString()!!
        thumbnailUrl = parcel.readString()!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(albumId)
        parcel.writeInt(userId)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(thumbnailUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResponseUsers> {
        override fun createFromParcel(parcel: Parcel): ResponseUsers {
            return ResponseUsers(parcel)
        }

        override fun newArray(size: Int): Array<ResponseUsers?> {
            return arrayOfNulls(size)
        }
    }

}

