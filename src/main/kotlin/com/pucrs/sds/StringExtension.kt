package com.pucrs.sds

import javax.xml.bind.DatatypeConverter

fun String.toHexByteArray(): ByteArray = DatatypeConverter.parseHexBinary(this)