package com.an.github.data.remote.api

import java.io.InputStreamReader
import kotlin.io.readText
import kotlin.jvm.javaClass

class MockResponseFileReader(path: String) {

    val content: String

    init {
        val reader = InputStreamReader(
            this.javaClass.classLoader?.
            getResourceAsStream(path)
        )
        content = reader.readText()
        reader.close()
    }
}
