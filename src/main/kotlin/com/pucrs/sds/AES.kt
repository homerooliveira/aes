package com.pucrs.sds

import com.pucrs.sds.AES.OperationMode.CRYPT
import com.pucrs.sds.AES.OperationMode.DECRYPT
import com.pucrs.sds.AES.Transformation.ECB
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AES {

    enum class Transformation(val transformation: String) {
        ECB("AES"),
        CBC("AES/CBC/PKCS5Padding"),
        CTR("AES/CTR/NoPadding")
    }

    enum class OperationMode(val opmode: Int) {
        CRYPT(Cipher.ENCRYPT_MODE),
        DECRYPT(Cipher.DECRYPT_MODE)
    }

    fun crypt(key: String,
              text: String,
              transformation: Transformation = ECB,
              needApplyHash: Boolean = true): String {
        val secretKey = getSecretKey(key, needApplyHash)
        val iv = getIvParameterSpec(CRYPT, text)
        val aes = getCipherInstance(transformation, CRYPT, secretKey, iv)
        val encrypted = iv.iv + aes.doFinal(text.toByteArray())

        return encrypted.toHexString()
    }

    fun decrypt(
        key: String,
        text: String,
        transformation: Transformation = ECB,
        needApplyHash: Boolean = true): String {
        val secretKey = getSecretKey(key, needApplyHash)
        val iv = getIvParameterSpec(DECRYPT, text)
        val aes = getCipherInstance(transformation, DECRYPT, secretKey, iv)
        val byteArray = text.toHexByteArray()
        val decrypted = aes.doFinal(byteArray, 16, byteArray.count() - 16)

        return String(decrypted)
    }

    private fun getCipherInstance(transformation: Transformation,
                                  mode: OperationMode,
                                  secretKey: SecretKey,
                                  ivParameterSpec: IvParameterSpec? = null): Cipher {
        val cipher = Cipher.getInstance(transformation.transformation)

        return cipher.apply {
            if (ivParameterSpec != null) {
                init(mode.opmode, secretKey, ivParameterSpec)
            } else {
                init(mode.opmode, secretKey)
            }
        }
    }

    private fun getIvParameterSpec(mode: OperationMode, text: String): IvParameterSpec {
        return when (mode) {
            CRYPT -> {
                val secureRandom = SecureRandom()
                val bytes = ByteArray(16)
                secureRandom.nextBytes(bytes)
                IvParameterSpec(bytes)
            }
            DECRYPT -> {
                val ivString = text.substring(0, 32) // Get Iv from text
                IvParameterSpec(ivString.toHexByteArray())
            }
        }
    }

    private fun getSecretKey(key: String, needApplyHash: Boolean = false): SecretKey {
        val keyBytes = key.toByteArray()

        val bytes = if (needApplyHash) {
            val messageDigest = MessageDigest.getInstance("SHA-256")
            messageDigest.update(keyBytes)
            messageDigest.digest().copyOfRange(0, 16)
        } else {
            key.toHexByteArray()
        }

        return SecretKeySpec(bytes, "AES")
    }
}
