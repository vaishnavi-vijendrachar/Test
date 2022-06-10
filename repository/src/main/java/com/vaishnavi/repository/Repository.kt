package com.vaishnavi.repository

import kotlinx.coroutines.flow.flow
import org.koin.java.KoinJavaComponent.inject

class Repository {

    private val service: RemoteService by inject(RemoteService::class.java)

    fun getUserDataFromRemote() = flow { emit(service.getDataFromRemote()) }
}


