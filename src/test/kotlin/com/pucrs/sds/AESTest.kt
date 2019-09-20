package com.pucrs.sds

import com.pucrs.sds.AES.Transformation.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class AESTest {

    @Test
    fun cipherAndDecipherNone() {
        val password = "my name"
        val plainText = "test"

        val cipher = AES.crypt(password, plainText)

        val decipher = AES.decrypt(password, cipher)

        assertEquals(plainText, decipher)
    }

    @Test
    fun cryptAndDecryptCTR() {
        val key = "36f18357be4dbd77f050515c73fcf9f2"
        val cipher = "770b80259ec33beb2561358a9f2dc617e46218c0a53cbeca695ae45faa8952aa0e311bde9d4e01726d3184c34451"

        val decrypt = AES.decrypt(key, cipher, CTR)

        assertEquals("bla", decrypt)
    }
}