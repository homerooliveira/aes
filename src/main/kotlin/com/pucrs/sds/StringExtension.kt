package com.pucrs.sds

import org.apache.commons.codec.binary.Hex

fun String.toHexByteArray(): ByteArray = Hex.decodeHex(this)