package com.example.androidtask.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ResponsePosts() : Parcelable {

    @SerializedName("userId")
    var userId : Int = 0

    @SerializedName("id")
    var id : Int = 0

    @SerializedName("title")
    var title : String = ""

    @SerializedName("body")
    var body : String = ""

    constructor(parcel: Parcel) : this() {
        userId = parcel.readInt()
        id = parcel.readInt()
        title = parcel.readString()!!
        body = parcel.readString()!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userId)
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResponsePosts> {
        override fun createFromParcel(parcel: Parcel): ResponsePosts {
            return ResponsePosts(parcel)
        }

        override fun newArray(size: Int): Array<ResponsePosts?> {
            return arrayOfNulls(size)
        }
    }
}