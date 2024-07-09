package com.example.ehasibu.product.data

import android.os.Parcel
import android.os.Parcelable

data class EditRequest(
    val productId: String,
    val productName: String,
    val description: String,
    val category: String,
    val unit: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )


    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<EditRequest> {
        override fun createFromParcel(parcel: Parcel): EditRequest {
            return EditRequest(parcel)
        }

        override fun newArray(size: Int): Array<EditRequest?> {
            return arrayOfNulls(size)
        }
    }
}
