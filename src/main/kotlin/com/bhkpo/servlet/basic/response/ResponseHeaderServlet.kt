package com.bhkpo.servlet.basic.response

import com.bhkpo.servlet.basic.HelloData
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "responseHeaderServlet", urlPatterns = ["/response-header"])
class ResponseHeaderServlet: HttpServlet() {
    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        // [status-line]
        resp.status = HttpServletResponse.SC_BAD_REQUEST

        // [response-headers]
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate")
        resp.setHeader("Pragma", "no-cache")
        resp.setHeader("my-header", "hello")

        // [Header 편의 메서드]
        content(resp)

        // [Cookie 편의 메서드]
        cookie(resp)

        redirect(resp)

        // [message body]
        resp.writer.println("Hello")
    }

    private fun content(response: HttpServletResponse) {
        // Content-Type: text/plain;charset=utf-8
        // Content-Length: 2
        // response.setHeader("Content-Type", "text/plain;charset=utf-8")
        response.contentType = "text/plain"
        response.characterEncoding = "utf-8"
        // response.setContentLength(2) // (생략 시 자동 생성)
    }

    private fun cookie(response: HttpServletResponse) {
        // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600")
        val cookie = Cookie("myCookie", "good")
        cookie.maxAge = 600 // 600초
        response.addCookie(cookie)
    }

    private fun redirect(response: HttpServletResponse) {
        // Status Code 302
        // Location: /basic/hello-form.html
        // 방법 1
        response.status = HttpServletResponse.SC_FOUND // 302
        response.setHeader("Location", "/basic/hello-form.html")
        // 방법 2
        response.sendRedirect("/basic/hello-form.html")
    }
}
