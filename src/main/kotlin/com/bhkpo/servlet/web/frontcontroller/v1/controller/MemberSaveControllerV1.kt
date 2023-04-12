package com.bhkpo.servlet.web.frontcontroller.v1.controller

import com.bhkpo.servlet.domain.member.Member
import com.bhkpo.servlet.domain.member.MemberRepository
import com.bhkpo.servlet.web.frontcontroller.v1.ControllerV1
import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MemberSaveControllerV1: ControllerV1 {

    private val memberRepository = MemberRepository.getInstance()

    override fun process(request: HttpServletRequest, response: HttpServletResponse) {
        val username: String = request.getParameter("username")
        val age: Int = request.getParameter("age").toInt()

        val member = Member(username, age)
        memberRepository.save(member)

        request.setAttribute("member", member)

        val viewPath = "/WEB-INF/views/save-result.jsp"
        val dispatcher: RequestDispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}