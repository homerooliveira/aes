package com.pucrs.sds

import com.pucrs.sds.AES.OperationMode.CRYPT
import com.pucrs.sds.AES.OperationMode.DECRYPT
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 *
 *
 *
 * @author Homero Oliveira
 */
object AES {

    enum class Transformation(val transformation: String) {
        CBC("AES/CBC/PKCS5Padding"),
        CTR("AES/CTR/NoPadding")
    }

    enum class OperationMode(val opmode: Int) {
        CRYPT(Cipher.ENCRYPT_MODE),
        DECRYPT(Cipher.DECRYPT_MODE)
    }

    private const val IV_SIZE = 16

    fun crypt(key: String, text: String, transformation: Transformation): String {
        val secretKey = getSecretKey(key)
        val iv = getIvForCrypt()
        val aes = getCipherInstance(transformation, CRYPT, secretKey, iv)
        val encrypted = iv.iv + aes.doFinal(text.toHexByteArray())

        return encrypted.toHexString()
    }

    fun decrypt(key: String, cipher: String, transformation: Transformation): String {
        val byteArray = cipher.toHexByteArray()
        val secretKey = getSecretKey(key)
        val iv = getIvForDecrypt(byteArray)
        val aes = getCipherInstance(transformation, DECRYPT, secretKey, iv)
        val decrypted = aes.doFinal(byteArray, IV_SIZE, byteArray.count() - IV_SIZE)

        return String(decrypted)
    }

    private fun getCipherInstance(transformation: Transformation,
                                  mode: OperationMode,
                                  secretKey: SecretKey,
                                  ivParameterSpec: IvParameterSpec
    ): Cipher {
        val cipher = Cipher.getInstance(transformation.transformation)

        return cipher.apply {
            init(mode.opmode, secretKey, ivParameterSpec)
        }
    }

    private fun getIvForCrypt(): IvParameterSpec {
        val secureRandom = SecureRandom()
        val bytes = ByteArray(IV_SIZE)
        secureRandom.nextBytes(bytes)
        return IvParameterSpec(bytes)
    }

    private fun getIvForDecrypt(cipherByteArray: ByteArray): IvParameterSpec {
        return IvParameterSpec(cipherByteArray.copyOf(IV_SIZE))
    }

    private fun getSecretKey(key: String): SecretKey {
        val bytes = key.toHexByteArray()
        return SecretKeySpec(bytes, "AES")
    }
}
