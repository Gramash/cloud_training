package com.cloud.training.api

import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.math.BigDecimal

fun stringToBigDecimal(string: String?): BigDecimal {
    return try {
        BigDecimal(string)
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("price format is invalid")
    }
}