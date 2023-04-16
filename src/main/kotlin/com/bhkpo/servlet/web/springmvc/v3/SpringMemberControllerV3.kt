package com.bhkpo.servlet.web.springmvc.v3

import com.bhkpo.servlet.domain.member.Member
import com.bhkpo.servlet.domain.member.MemberRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/springmvc/v3/members")
class SpringMemberControllerV3 {

    private val memberRepository = MemberRepository.getInstance()

//    @RequestMapping(value = ["/new-form"], method  = [RequestMethod.GET])
    @GetMapping("/new-form")
    fun newForm(): String {
        return "new-form"
    }

//    @RequestMapping(method = [RequestMethod.GET])
    @GetMapping
    fun members(model: Model): String {
        val members: List<Member> = memberRepository.findAll()
        model.addAttribute("members", members)
        return "members"
    }

//    @RequestMapping(value = ["/save"], method = [RequestMethod.POST])
    @PostMapping("/save")
    fun save(
        @RequestParam("username") username: String,
        @RequestParam("age") age: Int,
        model: Model
    ): String {
        val member = Member(username, age)
        memberRepository.save(member)

        model.addAttribute("member", member)
        return "save-result"
    }
}
