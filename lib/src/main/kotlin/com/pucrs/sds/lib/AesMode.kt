package com.pucrs.sds.lib

enum class AesMode(val value: String) {
    CBC("AES/CBC/PKCS5Padding"),
    CTR("AES/CTR/NoPadding")
}