package com.cloud.training.persistance

import com.cloud.training.persistance.Product.Companion.Relation.PRODUCT_2_CATEGORY
import com.cloud.training.persistance.Product.Companion.Relation.SOURCE
import com.cloud.training.persistance.Product.Companion.Relation.TARGET
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, String> {
    @Query(SELECT_PRODUCTS_FOR_CATEGORY_CODE, nativeQuery = true)
    fun findAllProductsForCategoryCode(@Param("code") code: String): List<Product>

    companion object {
        const val SELECT_PRODUCTS_FOR_CATEGORY_CODE = """
            SELECT * FROM ${Product.TABLE}
            JOIN $PRODUCT_2_CATEGORY
                ON $PRODUCT_2_CATEGORY.$SOURCE = ${Product.TABLE}.${Product.CODE}
            JOIN ${Category.TABLE}
                ON $PRODUCT_2_CATEGORY.$TARGET = ${Category.TABLE}.${Category.CODE}
            WHERE ${Category.TABLE}.${Category.CODE} = :code
        """
    }
}

@Repository
interface CategoryRepository : JpaRepository<Category, Long>

