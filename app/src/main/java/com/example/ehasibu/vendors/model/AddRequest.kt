package com.example.ehasibu.vendors.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddRequest(
    val address: String,
    val displayName: String,
    val email: String,
    val otherDetails: String,
    val phone: String,
    val vendorName: String,
    val vendorPin: String,
    val vendorType: String
): Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }
}


