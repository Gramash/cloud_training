package com.cloud.training.persistance

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = Product.TABLE)
data class Product(
        @Id val code: String,
) {
    var name: String? = null
    var price: BigDecimal = BigDecimal.ONE.negate()

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, targetEntity = Category::class)
    @JoinTable(
            name = Relation.PRODUCT_2_CATEGORY,
            joinColumns = [JoinColumn(name = "product_id", referencedColumnName = CODE)],
            inverseJoinColumns = [JoinColumn(name = "category_id", referencedColumnName = Category.CODE)]
    )
    @JsonManagedReference
    var categories: Set<Category> = mutableSetOf()
        get() = field.toSet()

    companion object {
        const val TABLE = "products"
        const val CODE = "code"
        const val CATEGORIES = "categories"

        object Relation {
            const val PRODUCT_2_CATEGORY = "products2categories"
            const val SOURCE = "product_id"
            const val TARGET = "category_id"
        }
    }
}

@Entity(name = Category.TABLE)
data class Category(
        @Id val code: String,
) {
    val name: String? = null

    @ManyToMany(mappedBy = Product.CATEGORIES, targetEntity = Product::class, fetch = FetchType.EAGER)
    @JsonBackReference
    var products: Set<Product> = mutableSetOf()
        get() = field.toSet()

    companion object {
        const val TABLE = "categories"
        const val CODE = "code"
        const val NAME = "name"
    }
}


