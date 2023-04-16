package com.bhkpo.servlet.web.springmvc.v1

import com.bhkpo.servlet.domain.member.Member
import com.bhkpo.servlet.domain.member.MemberRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class SpringMemberSaveControllerV1 {

    private val memberRepository = MemberRepository.getInstance()

    @RequestMapping("/springmvc/v1/members/save")
    fun process(request: HttpServletRequest, response: HttpServletResponse): ModelAndView {
        val username: String = request.getParameter("username")
        val age: Int = request.getParameter("age").toInt()

        val member = Member(username, age)
        memberRepository.save(member)

        val mv = ModelAndView("save-result")
        mv.addObject("member", member)
        return mv
    }
}
