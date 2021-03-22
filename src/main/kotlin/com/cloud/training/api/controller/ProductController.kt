package com.cloud.training.api.controller

import com.cloud.training.api.stringToBigDecimal
import com.cloud.training.persistance.Product
import com.cloud.training.persistance.service.ProductService
import com.cloud.training.persistance.service.product.priceMoreThan
import com.cloud.training.persistance.service.product.priceLessThen
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import javax.annotation.Resource

@RestController
@RequestMapping("/product")
class ProductController {

    @Resource
    private lateinit var productService: ProductService

    @GetMapping
    fun getProducts(
            @RequestParam(required = false) lowestPrice: String?,
            @RequestParam(required = false) highestPrice: String?,
            @RequestParam(required = false) category: String?,
    ): List<Product> {
        var products = productService.findProductsForCategoryCode(category);

        products = applyFilters(lowestPrice, products, highestPrice)
        return products as List<Product>
    }

    @GetMapping("/{code}")
    fun getProduct(
            @PathVariable code: String,
    ): Product {
        return productService.findProductForCode(code)
                .orElseThrow()
    }

    private fun applyFilters(
            lowestPrice: String?,
            products: Iterable<Product>,
            highestPrice: String?,
    ): Iterable<Product> {
        var result = products
        val (low, high) = convertToBigDecimal(lowestPrice, highestPrice)
        low?.let {
            result = products.priceMoreThan(it)
        }
        high?.let {
            result = products.priceLessThen(it)
        }
        return result
    }

    private fun convertToBigDecimal(lowestPrice: String?, highestPrice: String?): Prices {
        return Prices(
                stringToBigDecimal(lowestPrice),
                stringToBigDecimal(highestPrice)
        )
    }

    data class Prices(
            val lowestPrice: BigDecimal?,
            val highestPrice: BigDecimal?,
    )

}

