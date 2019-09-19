package com.pucrs.sds

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class AESTest {

    @Test
    fun cipherAndDecipher() {
        val password = "my name"
        val plainText = "test"

        val cipher = AES.crypt(password, plainText)

        val decipher = AES.decrypt(password, cipher)

        assertEquals(plainText, decipher)
    }
}