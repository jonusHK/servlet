package com.bhkpo.servlet.web.frontcontroller.v2

import com.bhkpo.servlet.web.frontcontroller.MyView
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

interface ControllerV2 {
    fun process(request: HttpServletRequest, response: HttpServletResponse): MyView
}
