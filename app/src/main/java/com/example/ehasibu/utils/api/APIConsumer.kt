package com.example.ehasibu.utils.api

import com.example.ehasibu.accounts.data.AccountRequest
import com.example.ehasibu.accounts.data.AccountsEditRequest
import com.example.ehasibu.accounts.data.AccountsEntity
import com.example.ehasibu.accounts.data.AccountsResponse
import com.example.ehasibu.bills.model.AddBillResponse
import com.example.ehasibu.bills.model.Bill
import com.example.ehasibu.bills.model.BillRequest
import com.example.ehasibu.bills.model.BillsResponse
import com.example.ehasibu.budget.data.AddBudgetResponse
import com.example.ehasibu.budget.data.BudgetRequest
import com.example.ehasibu.budget.data.Entity
import com.example.ehasibu.budget.data.UpdateBudgetRequest
import com.example.ehasibu.customerinformation.data.CustomerRequest
import com.example.ehasibu.customerinformation.data.CustomerResponse
import com.example.ehasibu.customerinformation.data.UpdateCustomerRequest
import com.example.ehasibu.expenses.model.DeleteExpenseResponse
import com.example.ehasibu.expenses.model.EditExpRequest
import com.example.ehasibu.expenses.model.ExpAddResponse
import com.example.ehasibu.expenses.model.ExpenseRequest
import com.example.ehasibu.expenses.model.ExpensesResponse
import com.example.ehasibu.login.ApiResponse
import com.example.ehasibu.login.data.AuthUserResponse
import com.example.ehasibu.login.data.OtpRequest
import com.example.ehasibu.login.data.OtpRequest2
import com.example.ehasibu.login.data.PassRequest
import com.example.ehasibu.login.data.PassResetRequest
import com.example.ehasibu.login.data.UserRequest
import com.example.ehasibu.login.forgot_password.ForgotPassResponse
import com.example.ehasibu.login.forgot_password.OtpValResponse
import com.example.ehasibu.login.reset_password.PasswordRequest
import com.example.ehasibu.product.data.DelResponse
import com.example.ehasibu.product.data.EditRequest
import com.example.ehasibu.product.data.PriceRequest
import com.example.ehasibu.product.data.ProdResponse
import com.example.ehasibu.product.data.ProductRequest
import com.example.ehasibu.product.data.ProductResponse
import com.example.ehasibu.product.data.SetPriceResponse
import com.example.ehasibu.productsales.data.SalesEntity
import com.example.ehasibu.productsales.data.SalesPResponse
import com.example.ehasibu.purchaseorder.data.ApproveResponse
import com.example.ehasibu.purchaseorder.data.OrderEntity
import com.example.ehasibu.purchaseorder.data.OrderResponse
import com.example.ehasibu.purchaseorder.data.PoRequest
import com.example.ehasibu.purchaseorder.data.PoResponse
import com.example.ehasibu.vendors.model.AddRequest
import com.example.ehasibu.vendors.model.AddVendorResponse
import com.example.ehasibu.vendors.model.DelVResponse
import com.example.ehasibu.vendors.model.EditResponse
import com.example.ehasibu.vendors.model.EditVRequest
import com.example.ehasibu.vendors.model.VendorResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface APIConsumer {

    @POST("auth/login")
    fun login(@Body loginRequest: UserRequest): Call<ApiResponse<AuthUserResponse>>

    @POST("auth/resetPassword")
    fun passwordReset(@Body loginRequest: PassRequest): Call<ApiResponse<PasswordRequest>>

    @POST("auth/verifyOTP")
    fun otpVerify(@Body loginRequest: OtpRequest): Call<ApiResponse<AuthUserResponse>>

    @POST("auth/sendForgotPasswordOtp")
    fun passOtpSend(@Body loginRequest: PassResetRequest): Call<ApiResponse<ForgotPassResponse>>

    @POST("auth/validateForgotPasswordOTP")
    fun otpValidate(@Body loginRequest: OtpRequest2): Call<ApiResponse<OtpValResponse>>

    @POST("products/add")
    suspend fun addProduct(@Body product: ProductRequest): Response<ApiResponse<ProductResponse>>


    @GET("products/findAllProducts")
    suspend fun getAllProducts(): Response<ApiResponse<List<ProdResponse>>>

    @GET("products/Find by product Name")
    suspend fun fetchProduct(@Query("productName") productName: String): Response<ApiResponse<ProdResponse>>

    @DELETE("products/productId")
    suspend fun deleteProduct(@Query("productId") productId: String): Response<DelResponse>

    @PUT("products/adjustBuyingPrice")
    suspend fun setBuyingPrice(@Body request: PriceRequest): Response<SetPriceResponse>

    @PUT("products/updateProduct")
    suspend fun updateProduct(@Body product: EditRequest): Response<DelResponse>


    //productsales
    @GET("sales/findAllSales")
    suspend fun getSales(): Response<SalesPResponse<List<SalesEntity>>>


    //customers
    @POST("customers/customers")
    suspend fun createCustomer(@Body customer: CustomerRequest): Response<ApiResponse<CustomerResponse>>

    @GET("customers/customers")
    suspend fun getCustomers(): Response<List<CustomerResponse>>

    @PUT("customers/customers/{id}")
    suspend fun updateCustomer(@Body customer: UpdateCustomerRequest): Response<ApiResponse<CustomerResponse>>

    @DELETE("customers/{id}")
    suspend fun deleteCustomer(@Query("customerId") customerId: Long): Response<ApiResponse<CustomerResponse>>


    //Purchases
    @GET("purchases/getAllPurchases")
    suspend fun fetchOrders(): Response<OrderResponse<List<OrderEntity>>>

    @PUT("purchases/approvePurchase")
    suspend fun approveOrder(@Query("id") id: String): Response<ApproveResponse>

    @POST("purchases/addPurchase")
    suspend fun addOrder(@Body order: PoRequest): Response<ApiResponse<PoResponse>>


    @GET("vendors/fetchAll")
    suspend fun fetchVendors(): Response<VendorResponse>

    @PUT("vendors/update-vendor")
    suspend fun updateVendor(@Body editRequest: EditVRequest): Response<EditResponse>

    @DELETE("vendors/vendorId")
    suspend fun deleteVendor(@Query("vendorId") vendorId: String): Response<DelVResponse>

    @POST("vendors/add")
    suspend fun addVendor(@Body vendor: AddRequest): Response<AddVendorResponse>


    //Budgets
    @GET("budgets/budget")
    suspend fun fetchBudgets(): Response<List<Entity>>

    @POST("budgets/add")
    suspend fun addBudget(@Body budget: BudgetRequest): Response<AddBudgetResponse>

    @PUT("budgets/{budgetId}")
    suspend fun updateBudget(@Body budgetRequest: UpdateBudgetRequest): Response<AddBudgetResponse>

    //bills
    @POST("bills/createBill")
    suspend fun addBill(@Body bill: BillRequest): Response<AddBillResponse>

    @GET("bills/getAllBills")
    suspend fun fetchBills(): Response<BillsResponse<List<Bill>>>

    @PUT("bills/approve")
    suspend fun approveBill(@Query("id") id: String): Response<AddBillResponse>

    //Expenses
    @POST("addExpense")
    suspend fun addExpense(@Body expense: ExpenseRequest): Response<ExpAddResponse>

    @PUT("updateExpense")// Not okay, to be rectifiied.....
    suspend fun updateExpense(@Body expenseRequest: EditExpRequest): Response<ExpensesResponse>

    @DELETE("deleteExpense")
    suspend fun deleteExpense(@Query("expenseId") expenseId: Long): Response<DeleteExpenseResponse>

    @GET("findAllExpenses")
    suspend fun fetchExpenses(): Response<ExpensesResponse>


    //accounts
    @GET("accounts")
    suspend fun fetchAccounts(): Response<ApiResponse<List<AccountsEntity>>>

    @POST("accounts/add")
    suspend fun addAccount(@Body account: AccountRequest): Response<AccountsResponse>

    @PUT("accounts/{accountCode}")
    suspend fun updateAccount(@Body accountEdit: AccountsEditRequest) : Response<AccountsResponse>

    @DELETE("accounts/delete")
    suspend fun deleteAccount(@Query("accountCode") accountCode: Int) : Response<AccountsResponse>


}


