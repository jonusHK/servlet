package com.bhkpo.servlet.web.frontcontroller.v3.controller

import com.bhkpo.servlet.domain.member.Member
import com.bhkpo.servlet.domain.member.MemberRepository
import com.bhkpo.servlet.web.frontcontroller.ModelView
import com.bhkpo.servlet.web.frontcontroller.v3.ControllerV3

class MemberListControllerV3: ControllerV3 {
    private val memberRepository = MemberRepository.getInstance()

    override fun process(paramMap: Map<String, String>): ModelView {
        val members: List<Member> = memberRepository.findAll()
        val mv = ModelView("members")
        mv.model["members"] = members
        return mv
    }
}
