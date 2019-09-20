package com.pucrs.sds

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ByteArrayExtensionTest {

    @Test
    fun toHexString() {
        val text = "This is a sentence to be encrypted using AES and CTR mode."
        val hexText = text.toByteArray().toHexString().toLowerCase()
        val expected = "5468697320697320612073656e74656e636520746f20626520656e63727970746564207573696e672041455320616e6420435452206d6f64652e"
        assertEquals(expected, hexText)
    }
}