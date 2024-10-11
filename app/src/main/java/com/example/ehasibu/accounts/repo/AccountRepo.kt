package com.example.ehasibu.accounts.repo

import com.example.ehasibu.AppModule
import com.example.ehasibu.accounts.data.AccountRequest
import com.example.ehasibu.accounts.data.AccountsEditRequest
import com.example.ehasibu.accounts.data.AccountsEntity
import com.example.ehasibu.accounts.data.AccountsResponse
import com.example.ehasibu.login.ApiResponse
import retrofit2.Response

class AccountRepo (private val token : String) {
   private val apiConsumer = AppModule().getRetrofitInstance(token)


   suspend fun getAllAccounts(): Response<ApiResponse<List<AccountsEntity>>> {
      return apiConsumer.fetchAccounts()
   }

   suspend fun createAccount(account: AccountRequest) : Response<AccountsResponse> {
      return apiConsumer.addAccount(account)
   }

   suspend fun updateAccount(accountEdit : AccountsEditRequest) : Response<AccountsResponse> {
      return apiConsumer.updateAccount(accountEdit)
   }

   suspend fun deleteAccount(accountCode : Int) : Response<AccountsResponse>{
      return apiConsumer.deleteAccount(accountCode)
   }
}