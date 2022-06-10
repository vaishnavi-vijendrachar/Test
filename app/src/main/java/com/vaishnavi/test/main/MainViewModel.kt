package com.vaishnavi.test.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaishnavi.model.Response
import com.vaishnavi.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MainViewModel : ViewModel() {

    private val repository: Repository by inject(Repository::class.java)
    private val _response = MutableStateFlow<Response?>(null)
    val response: StateFlow<Response?> = _response
    private val _error = MutableStateFlow<Boolean?>(false)
    val error: StateFlow<Boolean?> = _error

    /**
     * get data from server and return the list back
     */
    fun getDataFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getUserDataFromRemote()
                .catch { exception ->
                    _error.value = true
                    print(exception)
                }
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    _response.value = result
                    Log.d("response", ""+ result )
                }
        }

    }

}
