package com.example.ehasibu.bills.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bill(
    val address: Any,
    val amountForVAT: Int,
    val amountPayable: Int,
    val billedAmount: Int,
    val createdAt: String,
    val deleted: Boolean,
    val getAmountPayable: Any,
    val id: Int,
    val inputTax: Int,
    val invoicePath: Any,
    val paymentDate: String,
    val paymentStatus: String,
    val poNumber: String,
    val status: String,
    val vendor: Vendor,
    val withholdingTaxPayable: Int
): Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }
}