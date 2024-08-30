package com.example.ehasibu.vendors.moddel

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Entity(
    val address: String,
    val bills: List<Any>,
    val createdAt: String,
    val deleted: Boolean,
    val displayName: String,
    val email: String,
    val otherDetails: String,
    val phone: String,
    val vendorAccountNumber: Any,
    val vendorId: String,
    val vendorName: String,
    val vendorPin: String,
    val vendorType: String
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }
}