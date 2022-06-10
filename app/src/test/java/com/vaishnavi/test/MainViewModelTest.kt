package com.vaishnavi.test

import com.vaishnavi.model.Response
import com.vaishnavi.repository.ApiClient
import com.vaishnavi.repository.Repository
import com.vaishnavi.test.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.time.ExperimentalTime

class MainViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @ExperimentalTime
    private lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var mockPage: Response

    @Mock
    private lateinit var mockRepository: Repository

    private val modules: Module
            by lazy {
                module {
                    single { Repository() }
                    single { ApiClient().buildService }
                }
            }

    @ExperimentalTime
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        startKoin {
            modules(emptyList())
        }
        loadKoinModules(modules)
        mainViewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        unloadKoinModules(modules)
        stopKoin()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `given MainViewModel when successful`() {
        coroutineScope.runBlockingTest {
            val flowResponse = flow {
                emit(mockPage)
            }
            whenever(mockRepository.getUserDataFromRemote()).thenReturn(flowResponse)
            val response = mainViewModel.getDataFromServer()
            assertNotNull(response)

        }
    }

    @ExperimentalTime
    @Test
    fun `given MainViewModel when response is not null`() {
        coroutineScope.runBlockingTest {
            whenever(mockRepository.getUserDataFromRemote()).thenReturn(emptyFlow())
            val response = mainViewModel.getDataFromServer()
            assertNotNull(response)
        }
    }
}