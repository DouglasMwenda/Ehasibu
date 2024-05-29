package com.example.ehasibu.login.repository
/*
import com.example.ehasibu.login.data.User
import com.example.ehasibu.login.utils.APIConsumer
import com.example.ehasibu.login.utils.RequestStatus
import com.example.ehasibu.login.utils.SimplifiedMessage
import kotlinx.coroutines.flow.flow

class AuthRepository(val consumer: APIConsumer) {
    fun user (body: User) = flow {
        emit(RequestStatus.waiting)
        val response = consumer.User.(body)
        if (response.isSuccessful) {
            emit(RequestStatus.success(response.body()!!))
        } else {
            emit(
                RequestStatus.Error(
                    SimplifiedMessage.get(
                        response.errorBody()!!.byteStream().reader().readText()
                    )
                )
            )
        }
    }
}*/