package com.pucrs.sds

import com.pucrs.sds.AES.OperationMode.DECRYPT
import com.pucrs.sds.AES.OperationMode.ENCRYPT
import com.pucrs.sds.AES.Transformation.*
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AES {

    enum class Transformation(val transformation: String) {
        NONE("AES"),
        CBC("AES/CBC/PKCS5Padding"),
        CTR("AES/CTR/NoPadding")
    }

    enum class OperationMode(val opmode: Int) {
        ENCRYPT(Cipher.ENCRYPT_MODE),
        DECRYPT(Cipher.DECRYPT_MODE)
    }

    fun crypt(password: String, text: String, transformation: Transformation = NONE): String {
        val aes = getCipherInstance(transformation, ENCRYPT, password, text)
        val encrypted = aes.doFinal(text.toByteArray())
        return encrypted.toHexString()
    }

    fun decrypt(password: String, text: String, transformation: Transformation = NONE): String {
        val aes = getCipherInstance(transformation, DECRYPT, password, text)
        val decrypted = aes.doFinal(text.substring(32).toHexByteArray())
        return String(decrypted)
    }

    private fun getCipherInstance(transformation: Transformation,
                                  mode: OperationMode, 
                                  password: String,
                                  text: String): Cipher {
        val cipher = Cipher.getInstance(transformation.transformation)
        val secretKey = getSecretKey(password)

        return cipher.apply {
            when (transformation) {
                CBC -> {
                    val random = SecureRandom()
                    val byteArray = ByteArray(16)
                    random.nextBytes(byteArray)
                    val ivParameterSpec = IvParameterSpec(byteArray)
                    init(mode.opmode, secretKey, ivParameterSpec)
                }
                CTR -> {
                    val ivParameterSpec = IvParameterSpec(text.substring(0, 32).toHexByteArray())
                    init(mode.opmode, secretKey, ivParameterSpec)
                }
                NONE -> {
                    init(mode.opmode, secretKey)
                }
            }
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
