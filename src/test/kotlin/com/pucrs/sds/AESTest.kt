package com.pucrs.sds

import com.pucrs.sds.AES.Transformation.CBC
import com.pucrs.sds.AES.Transformation.CTR
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
class AESTest {

    @Disabled
    @Test
    fun cipherAndDecipherNone() {
        val password = "my name"
        val plainText = "test"
        val cipher = AES.crypt(password, plainText)
        val decipher = AES.decrypt(password, cipher)

        assertEquals(plainText, decipher)
    }

    @Test
    fun task1() {
        val key = "140b41b22a29beb4061bda66b6747e14"
        val cipher = "4ca00ff4c898d61e1edbf1800618fb2828a226d160dad07883d04e008a7897ee2e4b7465d5290d0c0e6c6822236e1daafb94ffe0c5da05d9476be028ad7c1d81"
        val expected = "Basic CBC mode encryption needs padding."
        val actual = AES.decrypt(key, cipher, CBC, false)

        assertEquals(expected, actual)
    }

    @Test
    fun task2() {
        val key = "140b41b22a29beb4061bda66b6747e14"
        val cipher = "5b68629feb8606f9a6667670b75b38a5b4832d0f26e1ab7da33249de7d4afc48e713ac646ace36e872ad5fb8a512428a6e21364b0c374df45503473c5242a253"
        val expected = "Our implementation uses rand. IV"
        val actual = AES.decrypt(key, cipher, CBC, false)

        assertEquals(expected, actual)
    }

    @Test
    fun task3() {
        val key = "36f18357be4dbd77f050515c73fcf9f2"
        val cipher = "69dda8455c7dd4254bf353b773304eec0ec7702330098ce7f7520d1cbbb20fc388d1b0adb5054dbd7370849dbf0b88d393f252e764f1f5f7ad97ef79d59ce29f5f51eeca32eabedd9afa9329"
        val expected = "CTR mode lets you build a stream cipher from a block cipher."
        val actual = AES.decrypt(key, cipher, CTR, false)

        assertEquals(expected, actual)
    }

    @Test
    fun task4() {
        val key = "36f18357be4dbd77f050515c73fcf9f2"
        val cipher = "770b80259ec33beb2561358a9f2dc617e46218c0a53cbeca695ae45faa8952aa0e311bde9d4e01726d3184c34451"
        val expected = "Always avoid the two time pad!"
        val actual = AES.decrypt(key, cipher, CTR, false)

        assertEquals(expected, actual)
    }

    @Test
    fun task5() {
        val key = "36f18357be4dbd77f050515c73fcf9f2"
        val plainText = String("5468697320697320612073656e74656e636520746f20626520656e63727970746564207573696e672041455320616e6420435452206d6f64652e".toHexByteArray())
        val expected = "This is a sentence to be encrypted using AES and CTR mode."
        val cipher = AES.crypt(key, plainText, CTR, false)
        val actual = AES.decrypt(key, cipher, CTR, false)

        assertEquals(expected, actual)
    }

    @Test
    fun task6() {
        val key = "36f18357be4dbd77f050515c73fcf9f2"
        val plainText = String("4e657874205468757273646179206f6e65206f66207468652062657374207465616d7320696e2074686520776f726c642077696c6c2066616365206120626967206368616c6c656e676520696e20746865204c696265727461646f72657320646120416d6572696361204368616d70696f6e736869702e".toHexByteArray())
        val expected = "Next Thursday one of the best teams in the world will face a big challenge in the Libertadores da America Championship."
        val cipher = AES.crypt(key, plainText, CBC, false)
        val actual = AES.decrypt(key, cipher, CBC, false)

        assertEquals(expected, actual)
    }
}

