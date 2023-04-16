package com.bhkpo.servlet.web.frontcontroller.v3.controller

import com.bhkpo.servlet.domain.member.Member
import com.bhkpo.servlet.domain.member.MemberRepository
import com.bhkpo.servlet.web.frontcontroller.ModelView
import com.bhkpo.servlet.web.frontcontroller.v3.ControllerV3

class MemberSaveControllerV3: ControllerV3 {
    private val memberRepository = MemberRepository.getInstance()

    override fun process(paramMap: Map<String, String>): ModelView {
        val username: String = paramMap["username"]!!
        val age: Int = paramMap["age"]!!.toInt()

        val member = Member(username, age)
        memberRepository.save(member)

        val mv = ModelView("save-result")
        mv.model["member"] = member
        return mv
    }
}
