package com.pucrs.sds.lib

import org.apache.commons.codec.binary.Hex

fun ByteArray.toHexString(): String = Hex.encodeHexString(this)