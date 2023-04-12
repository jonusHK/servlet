package com.bhkpo.servlet.web.servlet

import com.bhkpo.servlet.domain.member.Member
import com.bhkpo.servlet.domain.member.MemberRepository
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "memberSaveServlet", urlPatterns = ["/servlet/members/save"])
class MemberSaveServlet: HttpServlet() {
    private val memberRepository = MemberRepository.getInstance()

    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        val username: String = req.getParameter("username")
        val age: Int = req.getParameter("age").toInt()

        val member = Member(username, age)
        memberRepository.save(member)

        resp.contentType = "text/html"
        resp.characterEncoding = "utf-8"

        resp.writer.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id=" + member.id + "</li>\n" +
                "    <li>username=" + member.username + "</li>\n" +
                "    <li>age=" + member.age + "</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>\n")
    }
}
