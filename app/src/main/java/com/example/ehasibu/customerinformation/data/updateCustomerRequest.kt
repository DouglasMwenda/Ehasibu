package com.example.ehasibu.customerinformation.data

import android.os.Parcel
import android.os.Parcelable

data class UpdateCustomerRequest(
    val customerId: Int,
    val customerType: String,
    val customerFirstName: String,
    val customerLastName: String,
    val phoneNumber: String,
    val emailAddress: String,
    val companyName: String,
    val address: String,

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),

    )

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<UpdateCustomerRequest> {
        override fun createFromParcel(parcel: Parcel): UpdateCustomerRequest {
            return UpdateCustomerRequest(parcel)
        }

        override fun newArray(size: Int): Array<UpdateCustomerRequest?> {
            return arrayOfNulls(size)
        }
    }
}
