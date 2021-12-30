package com.pucrs.sds

import org.apache.commons.codec.binary.Hex

fun ByteArray.toHexString(): String = Hex.encodeHexString(this)