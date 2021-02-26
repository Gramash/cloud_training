package com.cloud.training.functions

import java.util.ResourceBundle

const val resourceBaseName: String = "view.localization.messages"
val viewMessages: ResourceBundle = ResourceBundle.getBundle(resourceBaseName)

fun getProp(key: String): String {
    return viewMessages.getString(key)
}