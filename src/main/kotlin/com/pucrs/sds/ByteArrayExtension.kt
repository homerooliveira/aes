package com.pucrs.sds

import javax.xml.bind.DatatypeConverter

fun ByteArray.toHexString(): String = DatatypeConverter.printHexBinary(this)