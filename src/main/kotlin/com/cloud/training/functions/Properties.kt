package com.cloud.training.functions

import org.springframework.util.ResourceUtils
import java.io.FileInputStream
import java.util.Properties

val file = ResourceUtils.getFile("classpath:view/messages/messages.properties")
val properties: Properties = Properties().also {
    it.load(FileInputStream(file))
}

fun getProp(key: String): String {
    return properties[key] as String
}