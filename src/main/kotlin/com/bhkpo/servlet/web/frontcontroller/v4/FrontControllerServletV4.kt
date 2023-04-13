package com.bhkpo.servlet.web.frontcontroller.v4

import com.bhkpo.servlet.web.frontcontroller.MyView
import com.bhkpo.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4
import com.bhkpo.servlet.web.frontcontroller.v4.controller.MemberListControllerV4
import com.bhkpo.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV4", urlPatterns = ["/front-controller/v4/*"])
class FrontControllerServletV4: HttpServlet() {

    private val controllerMap = HashMap<String, ControllerV4>()

    init {
        controllerMap["/front-controller/v4/members/new-form"] = MemberFormControllerV4()
        controllerMap["/front-controller/v4/members/save"] = MemberSaveControllerV4()
        controllerMap["/front-controller/v4/members"] = MemberListControllerV4()
    }


    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        println("FrontControllerServletV4.service")
        val requestUri: String = req.requestURI
        val controller: ControllerV4? = controllerMap[requestUri]

        controller?.let {
            val paramMap: Map<String, String> = createParamMap(req)
            val model = HashMap<String, Any>()
            val viewName: String = controller.process(paramMap, model)
            val view = viewResolver(viewName)
            view.render(model, req, resp)
        } ?: run {
            resp.status = HttpServletResponse.SC_NOT_FOUND
            return
        }
    }

    private fun viewResolver(viewName: String): MyView {
        return MyView("/WEB-INF/views/$viewName.jsp")
    }

    private fun createParamMap(req: HttpServletRequest): Map<String, String> {
        val paramMap = HashMap<String, String>()
        req.parameterNames.asIterator().forEachRemaining { paramName ->
            paramMap[paramName] = req.getParameter(paramName)
        }
        return paramMap
    }
}
