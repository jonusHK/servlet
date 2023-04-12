package com.bhkpo.servlet.basic.request

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = ["/request-param"])
class RequestParamServlet: HttpServlet() {
    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        println("[전체 파라미터 조회]")
        req.parameterNames.asIterator().forEachRemaining {
            println("$it = ${req.getParameter(it)}")
        }
        println()

        println("[단일 파라미터 조회]")
        val username: String = req.getParameter("username")
        val age: String = req.getParameter("age")
        println("username = $username")
        println("age = $age")
        println()

        println("[이름이 같은 복수 파라미터 조회]")
        val usernames: Array<String> = req.getParameterValues("username")
        for (name in usernames) {
            println("username = $name")
        }
        println()

        resp.writer.write("ok")
    }
}
