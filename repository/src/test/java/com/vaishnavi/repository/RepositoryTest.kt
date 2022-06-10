package com.vaishnavi.repository

import com.vaishnavi.model.Facts
import com.vaishnavi.model.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
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
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import org.mockito.kotlin.whenever
import java.util.*
import kotlin.time.ExperimentalTime


class RepositoryTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()
    private var mockClient: RemoteService = mock()

    @Mock
    private lateinit var response: Response

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
        var row = listOf<Facts>(Facts("facts title", "description", "image"))
        response = Response("title", row)
        mockClient.stub {
            onBlocking { getDataFromRemote() }.doReturn(
                response
            )
        }
    }

    @After
    fun tearDown() {
        unloadKoinModules(modules)
        stopKoin()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `given RemoteService when response of title is successful`() {
        runBlockingTest {
            //given
            whenever(mockClient.getDataFromRemote()).thenReturn(response)
            //when
            val response = mockClient.getDataFromRemote()
            //then
            assertEquals(response.rows.first().title, "facts title")
        }
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `given RemoteService when response of description is successful`() {
        runBlockingTest {
            //given
            whenever(mockClient.getDataFromRemote()).thenReturn(response)
            //when
            val response = mockClient.getDataFromRemote()
            //then
            assertEquals(response.rows.first().description, "description")
        }
    }

    @OptIn(ExperimentalTime::class, ExperimentalCoroutinesApi::class)
    @Test
    fun `given RemoteService when response of image is successful`() {
        runBlockingTest {
            //given
            whenever(mockClient.getDataFromRemote()).thenReturn(response)
            //when
            val response = mockClient.getDataFromRemote()
            //then
            assertEquals(response.rows.first().description, "image")
        }
    }
}