package com.bhkpo.servlet.web.frontcontroller.v3

import com.bhkpo.servlet.web.frontcontroller.ModelView
import com.bhkpo.servlet.web.frontcontroller.MyView
import com.bhkpo.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3
import com.bhkpo.servlet.web.frontcontroller.v3.controller.MemberListControllerV3
import com.bhkpo.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV3", urlPatterns = ["/front-controller/v3/*"])
class FrontControllerServletV3: HttpServlet() {

    private val controllerMap = HashMap<String, ControllerV3>()

    init {
        controllerMap["/front-controller/v3/members/new-form"] = MemberFormControllerV3()
        controllerMap["/front-controller/v3/members/save"] = MemberSaveControllerV3()
        controllerMap["/front-controller/v3/members/members"] = MemberListControllerV3()
    }


    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        println("FrontControllerServletV3.service")

        val requestUri: String = req.requestURI
        val controller: ControllerV3? = controllerMap[requestUri]

        controller?.let {
            val paramMap: Map<String, String> = createParamMap(req)
            val mv: ModelView = controller.process(paramMap)
            val view = viewResolver(mv.viewName)
            view.render(mv.model, req, resp)
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
