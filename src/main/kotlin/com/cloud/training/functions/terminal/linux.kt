package com.cloud.training.functions.terminal

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun exec(): StringBuilder {
    val runtime = Runtime.getRuntime()
    val process = runtime.exec("./scripts/test.sh")
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    var line: String?
    val builder = StringBuilder()
    while (reader.readLine().also { line = it } != null) {
        builder.append(line)
    }
    return builder
}
