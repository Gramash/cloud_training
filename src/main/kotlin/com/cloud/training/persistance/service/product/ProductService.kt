package com.cloud.training.persistance.service.product

import com.cloud.training.persistance.Product
import com.cloud.training.persistance.ProductRepository
import com.cloud.training.persistance.service.ProductService
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Service
class ProductServiceImpl(@Resource val productRepository: ProductRepository) : ProductService {
    override fun findAllProducts(): Iterable<Product> = productRepository.findAll()

    override fun findProductForCode(code: String) = productRepository.findById(code)

    override fun findProductsForCategoryCode(code: String?): Iterable<Product> {
        return if (code != null) {
            productRepository.findAllProductsForCategoryCode(code)
        } else {
            findAllProducts()
        }
    }
}

