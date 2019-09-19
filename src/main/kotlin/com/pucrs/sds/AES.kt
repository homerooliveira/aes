package com.pucrs.sds

import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object AES {

    fun crypt(password: String, text: String): String {
        val aes = getCipherInstance(Cipher.ENCRYPT_MODE, password)
        val encrypted = aes.doFinal(text.toByteArray())
        return encrypted.toHexString()
    }

    fun decrypt(password: String, text: String): String {
        val aes = getCipherInstance(Cipher.DECRYPT_MODE, password)
        val decrypted = aes.doFinal(text.toHexByteArray())
        return String(decrypted)
    }

    private fun getCipherInstance(opmode: Int, password: String): Cipher {
        return Cipher.getInstance("AES").apply {
            init(opmode, getSecretKey(password))
        }
    }

    private fun getSecretKey(password: String): SecretKey {
        val bytes = password.toByteArray()

        val messageDigest = MessageDigest.getInstance("SHA-256")
        messageDigest.update(bytes)

        val digestMessage = messageDigest.digest().copyOfRange(0, 16)
        return SecretKeySpec(digestMessage, "AES")
    }
}
