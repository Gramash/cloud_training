package com.cloud.training.persistance.service.category

import com.cloud.training.persistance.Category
import com.cloud.training.persistance.CategoryRepository
import com.cloud.training.persistance.service.CategoryService
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Service
class CategoryServiceImpl(@Resource val categoryRepository: CategoryRepository) : CategoryService {

    override fun findAllCategories(): Iterable<Category> {
        return categoryRepository.findAll()
    }

}

fun Iterable<Category>.sortedByProductSize(): Iterable<Category> {
    return this.sortedBy { it.products.size }
}