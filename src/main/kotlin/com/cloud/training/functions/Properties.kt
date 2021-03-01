package com.cloud.training.functions

import java.util.ResourceBundle

const val resourceBaseName: String = "view.localization.messages"
val viewMessagesBundle: ResourceBundle = ResourceBundle.getBundle(resourceBaseName)

fun getProp(key: String): String {
    return viewMessagesBundle.getString(key)
}