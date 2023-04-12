package com.bhkpo.servlet.basic.request

import jakarta.servlet.ServletInputStream
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.nio.charset.StandardCharsets
import org.springframework.util.StreamUtils

@WebServlet(name = "requestBodyStringServlet", urlPatterns = ["/request-body-string"])
class RequestBodyStringServlet: HttpServlet() {
    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        val inputStream: ServletInputStream = req.inputStream
        val messageBody: String = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
        println("messageBody = $messageBody")
        resp.writer.write("ok")
    }
}
