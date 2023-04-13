package com.bhkpo.servlet.web.frontcontroller.v4.controller

import com.bhkpo.servlet.domain.member.Member
import com.bhkpo.servlet.domain.member.MemberRepository
import com.bhkpo.servlet.web.frontcontroller.v4.ControllerV4

class MemberSaveControllerV4: ControllerV4 {
    private val memberRepository: MemberRepository = MemberRepository.getInstance()

    override fun process(paramMap: Map<String, String>, model: MutableMap<String, Any>): String {
        val username: String = paramMap["username"]!!
        val age: Int = paramMap["age"]!!.toInt()

        val member = Member(username, age)
        memberRepository.save(member)

        model["member"] = member
        return "save-result"
    }
}
