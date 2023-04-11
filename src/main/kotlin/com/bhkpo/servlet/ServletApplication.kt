package com.bhkpo.servlet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ServletComponentScan // 자동으로 서블릿 찾아서 등록
@SpringBootApplication
class ServletApplication

fun main(args: Array<String>) {
    runApplication<ServletApplication>(*args)
}
