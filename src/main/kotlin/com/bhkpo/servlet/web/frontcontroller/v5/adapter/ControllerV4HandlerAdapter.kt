package com.bhkpo.servlet.web.frontcontroller.v5.adapter

import com.bhkpo.servlet.web.frontcontroller.ModelView
import com.bhkpo.servlet.web.frontcontroller.v4.ControllerV4
import com.bhkpo.servlet.web.frontcontroller.v5.MyHandlerAdapter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class ControllerV4HandlerAdapter: MyHandlerAdapter {
    override fun supports(handler: Any): Boolean {
        return handler is ControllerV4
    }

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        val controller = handler as ControllerV4
        val paramMap: Map<String, String> = createParamMap(request)
        val model = HashMap<String, Any>()

        val viewName: String = controller.process(paramMap, model)
        val mv = ModelView(viewName)
        mv.model = model

        return mv
    }

    private fun createParamMap(req: HttpServletRequest): Map<String, String> {
        val paramMap = HashMap<String, String>()
        req.parameterNames.asIterator().forEachRemaining { paramName ->
            paramMap[paramName] = req.getParameter(paramName)
        }
        return paramMap
    }
}
