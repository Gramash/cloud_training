package com.cloud.training.persistance.service.product

import com.cloud.training.persistance.Product
import java.math.BigDecimal


fun Iterable<Product>.codeContains(string: String): Iterable<Product> {
    return this.sortedBy { it.code.contains(string) }
}

fun Iterable<Product>.priceMoreThan(other: BigDecimal?): Iterable<Product> {
    if (other == null) return this
    return this.filter { it.price > other }
}

fun Iterable<Product>.priceLessThen(other: BigDecimal?): Iterable<Product> {
    if (other == null) return this
    return this.filter { it.price < other }

}