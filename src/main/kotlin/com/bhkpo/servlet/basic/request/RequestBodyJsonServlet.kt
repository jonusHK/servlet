package com.bhkpo.servlet.basic.request

import com.bhkpo.servlet.basic.HelloData
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.servlet.ServletInputStream
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.nio.charset.StandardCharsets
import org.springframework.util.StreamUtils

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = ["/request-body-json"])
class RequestBodyJsonServlet: HttpServlet() {

    private val objectMapper = jacksonObjectMapper()
    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        val inputStream: ServletInputStream = req.inputStream
        val messageBody: String = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        val helloData: HelloData = objectMapper.readValue<HelloData>(messageBody)
        println("helloData.username = ${helloData.username}")
        println("helloData.age = ${helloData.age}")
        resp.writer.write("ok")
    }
}
