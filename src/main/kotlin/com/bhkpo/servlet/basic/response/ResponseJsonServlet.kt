package com.bhkpo.servlet.basic.response

import com.bhkpo.servlet.basic.HelloData
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "responseJsonServlet", urlPatterns = ["/response-json"])
class ResponseJsonServlet: HttpServlet() {

    private val objectMapper = jacksonObjectMapper()

    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.contentType = "application/json"
        resp.characterEncoding = "utf-8"

        val helloData = HelloData(username = "hello", age = 20)
        resp.writer.write(objectMapper.writeValueAsString(helloData))
    }
}