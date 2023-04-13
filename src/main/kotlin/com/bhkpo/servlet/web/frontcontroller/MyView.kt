package com.bhkpo.servlet.web.frontcontroller

import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MyView(private val viewPath: String) {
    fun render(request: HttpServletRequest, response: HttpServletResponse) {
        val dispatcher: RequestDispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }

    fun render(model: Map<String, Any>, request: HttpServletRequest, response: HttpServletResponse) {
        modelToRequestAttribute(model, request)
        val dispatcher: RequestDispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }

    private fun modelToRequestAttribute(model: Map<String, Any>, request: HttpServletRequest) {
        model.forEach { (key, value) ->
            request.setAttribute(key, value)
        }
    }
}
