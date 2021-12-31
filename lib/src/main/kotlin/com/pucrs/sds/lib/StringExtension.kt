package com.pucrs.sds.lib

import org.apache.commons.codec.binary.Hex

fun String.toHexByteArray(): ByteArray = Hex.decodeHex(this)