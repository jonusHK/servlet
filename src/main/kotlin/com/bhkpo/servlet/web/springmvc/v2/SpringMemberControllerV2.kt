package com.bhkpo.servlet.web.springmvc.v2

import com.bhkpo.servlet.domain.member.Member
import com.bhkpo.servlet.domain.member.MemberRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/springmvc/v2/members")
class SpringMemberControllerV2 {

    private val memberRepository = MemberRepository.getInstance()

    @RequestMapping("/new-form")
    fun newForm(): ModelAndView {
        return ModelAndView("new-form")
    }

    @RequestMapping
    fun members(): ModelAndView {
        val members: List<Member> = memberRepository.findAll()
        val mv = ModelAndView("members")
        mv.addObject("members", members)
        return mv
    }

    @RequestMapping("/save")
    fun save(request: HttpServletRequest, response: HttpServletResponse): ModelAndView {
        val username: String = request.getParameter("username")
        val age: Int = request.getParameter("age").toInt()

        val member = Member(username, age)
        memberRepository.save(member)

        val mv = ModelAndView("save-result")
        mv.addObject("member", member)
        return mv
    }
}
