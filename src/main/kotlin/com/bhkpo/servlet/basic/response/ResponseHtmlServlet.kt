package com.bhkpo.servlet.basic.response

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.PrintWriter


@WebServlet(name = "responeHtmlServlet", urlPatterns = ["/response-html"])
class ResponseHtmlServlet: HttpServlet() {
    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.contentType = "text/html"
        resp.characterEncoding = "utf-8"

        val writer: PrintWriter = resp.writer
        writer.println("<html>")
        writer.println("<body>")
        writer.println("  <div>Hello</div>")
        writer.println("</body>")
        writer.println("</html>")
    }
}