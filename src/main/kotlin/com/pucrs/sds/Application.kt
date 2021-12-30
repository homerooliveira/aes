package com.pucrs.sds

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.required

enum class Action {
    Decrypt, Encrypt
}

fun main(args: Array<String>) {
    val parser = ArgParser("aes")

    val action by parser.argument(ArgType.Choice<Action>())
    val password by parser.option(ArgType.String, shortName = "p", description = "Password used to decrypt/encrypt.")
        .required()
    val text by parser.option(ArgType.String, shortName = "t", description = "Cipher text to decrypt and plain text to encrypt.").required()
    val operation by parser.option(ArgType.Choice<AesMode>(), shortName = "o").required()

    parser.parse(args)

    when(action) {
        Action.Decrypt -> {
            val decrypt = AesAlgorithm.decrypt(password, text, operation)
            println(decrypt)
        }
        Action.Encrypt -> {
            val crypt = AesAlgorithm.encrypt(password, text, operation)
            println(crypt)
        }
    }
}