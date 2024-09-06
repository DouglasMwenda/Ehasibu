package com.example.ehasibu.budget.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpdateBudgetRequest(
    val amountBudgeted: Int,
    val amountSpent: Int,
    val budgetBalance: Int,
    val budgetId: Int,
    val budgetType: String,
    val date: String,
    val deleted: Boolean,
    val description: String,
    val expenseModel: List<ExpenseModelX>,
    val period: String
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}