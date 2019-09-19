package com.pucrs.sds

import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.count() < 3) {
        System.err.println("Need provide 3 arguments.")
        exitProcess(1)
    }

    val password = args[1]
    val file = File(args[2])
    val text = file.readText()

    var nameWithoutExtension = file.nameWithoutExtension

    when(args.first()) {
        "decrypt" -> {
            val decrypt = AES.decrypt(password, text)
            nameWithoutExtension = nameWithoutExtension.removeSuffix("crypt")
            File("${nameWithoutExtension}decrypt.txt").writeText(decrypt)
            println(decrypt)
        }
        "crypt" -> {
            val crypt = AES.crypt(password, text)
            nameWithoutExtension = nameWithoutExtension.removeSuffix("decrypt")
            File("${nameWithoutExtension}crypt.txt").writeText(crypt)
            println(crypt)
        }
        else -> exitProcess(1)
    }
}