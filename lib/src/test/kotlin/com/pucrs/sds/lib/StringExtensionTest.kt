package com.pucrs.sds.lib

import kotlin.test.Test
import kotlin.test.assertEquals

class StringExtensionTest {

    @Test fun toHexByteArray() {
        val hexText = "5468697320697320612073656e74656e636520746f20626520656e63727970746564207573696e672041455320616e6420435452206d6f64652e"
        val hexByteArray = hexText.toHexByteArray()
        val text = String(hexByteArray)
        assertEquals("This is a sentence to be encrypted using AES and CTR mode.", text)
    }
}