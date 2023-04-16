package com.bhkpo.servlet.web.frontcontroller.v2.controller

import com.bhkpo.servlet.domain.member.Member
import com.bhkpo.servlet.domain.member.MemberRepository
import com.bhkpo.servlet.web.frontcontroller.MyView
import com.bhkpo.servlet.web.frontcontroller.v2.ControllerV2
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MemberListControllerV2: ControllerV2 {
    private val memberRepository = MemberRepository.getInstance()

    override fun process(request: HttpServletRequest, response: HttpServletResponse): MyView {
        val members: List<Member> = memberRepository.findAll()
        request.setAttribute("members", members)
        return MyView("/WEB-INF/views/members.jsp")
    }
}
