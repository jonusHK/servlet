package com.bhkpo.servlet.basic.request

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "requestHeaderServlet", urlPatterns = ["/request-header"])
class RequestHeaderServlet: HttpServlet() {
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        printStartLine(request)
        printHeaders(request)
        printHeaderUtils(request)
        printEtc(request)
    }

    private fun printStartLine(request: HttpServletRequest) {
        println("--- REQUEST-LINE - start ---")
        println("request method = ${request.method}")
        println("request protocol = ${request.protocol}")
        println("request scheme = ${request.scheme}")
        println("request url = ${request.requestURL}")
        println("request uri = ${request.requestURI}")
        println("request queryString = ${request.queryString}")
        println("request isSecure = ${request.isSecure}")
        println("--- REQUEST-LINE - end ---")
        println()
    }

    private fun printHeaders(request: HttpServletRequest) {
        println("--- Headers - start ---")

//        val headerNames: Enumeration<String> = request.headerNames
//        while (headerNames.hasMoreElements()) {
//            val headerName: String = headerNames.nextElement()
//            println("$headerName: $headerName")
//        }

        request.headerNames.asIterator().forEachRemaining {
            println("$it: $it")
        }

        request.getHeader("host")

        println("--- Headers - end ---")
        println()
    }

    private fun printHeaderUtils(request: HttpServletRequest) {
        println("--- Header 편의 조회 start ---")
        println("[Host 편의 조회]")
        println("request serverName = ${request.serverName}")
        println("request serverPort = ${request.serverPort}")
        println()

        println("[Accept-Language 편의 조회]")
        request.locales.asIterator().forEachRemaining {
            println("locale = $it")
        }
        println("request locale = ${request.locale}")
        println()

        println("[cookie 편의 조회]")
        request.cookies.forEach {
            println("${it.name}: ${it.value}")
        }
        println()

        println("[Content 편의 조회]")
        println("request contentType = ${request.contentType}")
        println("request contentLength = ${request.contentLength}")
        println("request characterEncoding = ${request.characterEncoding}")
        println()
    }

    private fun printEtc(request: HttpServletRequest) {
        println("--- 기타 조회 start ---")
        println("[Remote 정보]")
        println("request remoteHost = ${request.remoteHost}")
        println("request remoteAddr = ${request.remoteAddr}")
        println("request remotePort = ${request.remotePort}")
        println()

        println("[Local 정보]")
        println("request localName = ${request.localName}")
        println("request localAddr = ${request.localAddr}")
        println("request localPort = ${request.localPort}")
        println("--- 기타 조회 start ---")
        println()
    }
}
