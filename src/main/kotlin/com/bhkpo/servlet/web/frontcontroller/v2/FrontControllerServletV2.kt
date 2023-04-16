package com.bhkpo.servlet.web.frontcontroller.v2

import com.bhkpo.servlet.web.frontcontroller.MyView
import com.bhkpo.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2
import com.bhkpo.servlet.web.frontcontroller.v2.controller.MemberListControllerV2
import com.bhkpo.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV2", urlPatterns = ["/front-controller/v2/*"])
class FrontControllerServletV2: HttpServlet() {

    private val controllerMap = HashMap<String, ControllerV2>()

    init {
        controllerMap["/front-controller/v2/members/new-form"] = MemberFormControllerV2()
        controllerMap["/front-controller/v2/members/save"] = MemberSaveControllerV2()
        controllerMap["/front-controller/v2/members"] = MemberListControllerV2()
    }


    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        println("FrontControllerServletV2.service")

        val requestUri: String = req.requestURI
        val controller: ControllerV2? = controllerMap[requestUri]
        controller?.let {
            val view: MyView = controller.process(req, resp)
            view.render(req, resp)
        } ?: run {
            resp.status = HttpServletResponse.SC_NOT_FOUND
            return
        }
    }
}
