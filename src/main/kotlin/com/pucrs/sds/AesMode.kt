package com.pucrs.sds

enum class AesMode(val value: String) {
    CBC("AES/CBC/PKCS5Padding"),
    CTR("AES/CTR/NoPadding")
}