package com.cloud.training.api.controller

import com.cloud.training.persistance.Category
import com.cloud.training.persistance.service.CategoryService
import com.cloud.training.persistance.service.category.sortedByProductSize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
@RequestMapping("/api/categories/")
class CategoriesController {

    @Resource
    lateinit var categoryService: CategoryService

    @GetMapping("/categories/sorted")
    fun getSortedByProductCount(): List<Category> {
        return categoryService.findAllCategories()
                .sortedByProductSize() as List<Category>
    }
}