package com.pucrs.sds

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.count() < 4) {
        System.err.println("Need provide 4 arguments.")
        exitProcess(1)
    }

    val password = args[1]
    val text = args[2]
    val operation = when (args[4]) {
        "CBC" -> AES.Transformation.CBC
        else -> AES.Transformation.CTR
    }

    when(args.first()) {
        "decrypt" -> {
            val decrypt = AES.decrypt(password, text, operation)
            println(decrypt)
        }
        "crypt" -> {
            val crypt = AES.crypt(password, text, operation)
            println(crypt)
        }
        else -> exitProcess(1)
    }
}