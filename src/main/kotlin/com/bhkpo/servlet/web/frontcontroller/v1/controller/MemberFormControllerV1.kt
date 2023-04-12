package com.bhkpo.servlet.web.frontcontroller.v1.controller

import com.bhkpo.servlet.web.frontcontroller.v1.ControllerV1
import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MemberFormControllerV1: ControllerV1 {
    override fun process(request: HttpServletRequest, response: HttpServletResponse) {
        val viewPath = "/WEB-INF/views/new-form.jsp"
        val dispatcher: RequestDispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}
