package com.bhkpo.servlet.web.frontcontroller.v5

import com.bhkpo.servlet.web.frontcontroller.ModelView
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

interface MyHandlerAdapter {
    fun supports(handler: Any): Boolean

    fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView
}
