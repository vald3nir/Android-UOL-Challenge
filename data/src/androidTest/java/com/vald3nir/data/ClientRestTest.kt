package com.vald3nir.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vald3nir.data.rest.RestClient
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ClientRestTest {


    @Test
    fun testListAllEvent() {
        runBlocking {
            val restClient = RestClient()
            val events = restClient.listAllEvents()
            Assert.assertNotNull(events)
            Assert.assertNotEquals(events!!.size, 0)
        }
    }

    @Test
    fun testGetEventFromIDValid() {
        runBlocking {
            val restClient = RestClient()
            val event = try {
                restClient.getEvent("1")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            Assert.assertNotNull(event)
        }
    }

    @Test
    fun testGetEventFromIDInvalid() {
        runBlocking {
            val restClient = RestClient()
            val event = try {
                restClient.getEvent("-1")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            Assert.assertNull(event)
        }
    }
}