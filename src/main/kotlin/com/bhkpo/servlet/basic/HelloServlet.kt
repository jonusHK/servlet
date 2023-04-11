package com.bhkpo.servlet.basic

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "helloServlet", urlPatterns = ["/hello"])
class HelloServlet: HttpServlet() {
    // Tomcat 등 서블릿 컨테이너가 이 request, response 를 구현해서 전달함
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        println("request = $request")
        println("response = $response")

        val username: String? = request.getParameter("username")
        println("username = $username")

        response.contentType = "text/plain"
        response.characterEncoding = "utf-8"

        response.writer.write("hello $username")
    }
}