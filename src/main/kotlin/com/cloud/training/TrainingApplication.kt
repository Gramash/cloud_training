package com.cloud.training

import com.cloud.training.persistance.*
import com.cloud.training.persistance.service.ProductService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TrainingApplication()

fun main(args: Array<String>) {

    var ctx = runApplication<TrainingApplication>(*args).also {
        val product1 = Product("prod1").also {
            it.categories = mutableSetOf(Category("cat1"))
        }
        val product2 = Product("prod2").also {
            it.categories = mutableSetOf(Category("cat1"))
        }
        val prod3 = Product("prod3").apply {
            this.categories = mutableSetOf(Category("cat3"))
        }

        val bean = it.getBean(ProductRepository::class.java)
        bean.saveAll(listOf(product1, product2, prod3))

        println(it.getBean(ProductService::class.java).findProductsForCategoryCode("cat1"))

    }

}
