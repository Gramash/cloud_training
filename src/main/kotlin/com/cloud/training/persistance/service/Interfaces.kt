package com.cloud.training.persistance.service

import com.cloud.training.persistance.Category
import com.cloud.training.persistance.Product
import java.util.*

interface ProductService {
    fun findAllProducts(): Iterable<Product>
    fun findProductForCode(code: String): Optional<Product>
    fun findProductsForCategoryCode(code: String?): Iterable<Product>
}

interface CategoryService {
    fun findAllCategories(): Iterable<Category>
}
