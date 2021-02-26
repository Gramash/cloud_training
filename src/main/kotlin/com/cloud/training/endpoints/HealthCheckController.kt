package com.cloud.training.endpoints

import com.cloud.training.functions.getProp
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class HealthCheckController {

    @GetMapping("/health-check")
    fun healthCheck(): String {
        return getProp("test")
    }

}