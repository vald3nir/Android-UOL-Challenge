package com.vald3nir.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.vald3nir.data.database.DatabaseHandler
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    @Test
    fun testListAllEvent() {
        val database = DatabaseHandler(InstrumentationRegistry.getInstrumentation().targetContext)
        val events = database.listEvents()
        Assert.assertNotNull(events)
        Assert.assertNotEquals(events!!.size, 0)
    }

    @Test
    fun testGetEventFromIDValid() {
        val database = DatabaseHandler(InstrumentationRegistry.getInstrumentation().context)
        val event = database.getEvent("1")
        Assert.assertNotNull(event)
    }

    @Test
    fun testGetEventFromIDInvalid() {
        val database = DatabaseHandler(InstrumentationRegistry.getInstrumentation().context)
        val event = database.getEvent("-1")
        Assert.assertNull(event)
    }
}