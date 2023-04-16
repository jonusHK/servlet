package com.bhkpo.servlet.web.frontcontroller.v5.adapter

import com.bhkpo.servlet.web.frontcontroller.ModelView
import com.bhkpo.servlet.web.frontcontroller.v3.ControllerV3
import com.bhkpo.servlet.web.frontcontroller.v5.MyHandlerAdapter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class ControllerV3HandlerAdapter: MyHandlerAdapter {
    override fun supports(handler: Any): Boolean {
        return handler is ControllerV3
    }

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        val controller = handler as ControllerV3
        val paramMap: Map<String, String> = createParamMap(request)
        return controller.process(paramMap)
    }

    private fun createParamMap(req: HttpServletRequest): Map<String, String> {
        val paramMap = HashMap<String, String>()
        req.parameterNames.asIterator().forEachRemaining { paramName ->
            paramMap[paramName] = req.getParameter(paramName)
        }
        return paramMap
    }
}
