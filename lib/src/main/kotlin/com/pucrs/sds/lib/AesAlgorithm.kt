package com.pucrs.sds.lib

import com.pucrs.sds.lib.AesAlgorithm.OperationMode.ENCRYPT
import com.pucrs.sds.lib.AesAlgorithm.OperationMode.DECRYPT
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
object AesAlgorithm {

    private enum class OperationMode(val opmode: Int) {
        ENCRYPT(Cipher.ENCRYPT_MODE),
        DECRYPT(Cipher.DECRYPT_MODE)
    }

    private const val IV_SIZE = 16
    private const val algorithm = "AES"

    fun encrypt(key: String, text: String, aesMode: AesMode): String {
        val secretKey = getSecretKey(key)
        val iv = getIvForCrypt()
        val aesCipher = getCipherInstance(aesMode, ENCRYPT, secretKey, iv)
        val encrypted = iv.iv + aesCipher.doFinal(text.toHexByteArray())

        return encrypted.toHexString()
    }

    fun decrypt(key: String, cipher: String, aesMode: AesMode): String {
        val byteArray = cipher.toHexByteArray()
        val secretKey = getSecretKey(key)
        val iv = IvParameterSpec(byteArray.copyOf(IV_SIZE))
        val aesCipher = getCipherInstance(aesMode, DECRYPT, secretKey, iv)
        val decrypted = aesCipher.doFinal(byteArray, IV_SIZE, byteArray.count() - IV_SIZE)

        return String(decrypted)
    }

    private fun getCipherInstance(
        aesMode: AesMode,
        mode: OperationMode,
        secretKey: SecretKey,
        ivParameterSpec: IvParameterSpec
    ): Cipher {
        val cipher = Cipher.getInstance(aesMode.value)

        return cipher.apply {
            init(mode.opmode, secretKey, ivParameterSpec)
        }
    }

    private fun getIvForCrypt(): IvParameterSpec {
        val bytes = ByteArray(IV_SIZE).also {
            val secureRandom = SecureRandom()
            secureRandom.nextBytes(it)
        }

        return IvParameterSpec(bytes)
    }

    private fun getSecretKey(key: String): SecretKey {
        val bytes = key.toHexByteArray()
        return SecretKeySpec(bytes, algorithm)
    }
}
