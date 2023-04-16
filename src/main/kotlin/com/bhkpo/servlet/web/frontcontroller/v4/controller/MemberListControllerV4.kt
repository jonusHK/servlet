package com.bhkpo.servlet.web.frontcontroller.v4.controller

import com.bhkpo.servlet.domain.member.Member
import com.bhkpo.servlet.domain.member.MemberRepository
import com.bhkpo.servlet.web.frontcontroller.v4.ControllerV4

class MemberListControllerV4: ControllerV4 {
    private val memberRepository = MemberRepository.getInstance()

    override fun process(paramMap: Map<String, String>, model: MutableMap<String, Any>): String {
        val members: List<Member> = memberRepository.findAll()
        model["members"] = members
        return "members"
    }
}
