package com.example.ehasibu.productsales

import androidx.lifecycle.LiveData

class SalesRepository {
    interface SalesRepository {
        fun getSalesData(): LiveData<List<SalesItem>>
    }



}
