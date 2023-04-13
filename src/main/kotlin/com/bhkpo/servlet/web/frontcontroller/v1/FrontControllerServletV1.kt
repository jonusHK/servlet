package com.bhkpo.servlet.web.frontcontroller.v1

import com.bhkpo.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1
import com.bhkpo.servlet.web.frontcontroller.v1.controller.MemberListControllerV1
import com.bhkpo.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV1", urlPatterns = ["/front-controller/v1/*"])
class FrontControllerServletV1: HttpServlet() {

    private val controllerMap = HashMap<String, ControllerV1>()

    init {
        controllerMap["/front-controller/v1/members/new-form"] = MemberFormControllerV1()
        controllerMap["/front-controller/v1/members/save"] = MemberSaveControllerV1()
        controllerMap["/front-controller/v1/members"] = MemberListControllerV1()
    }


    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        println("FrontControllerServletV1.service")

        val requestUri: String = req.requestURI
        val controller: ControllerV1? = controllerMap[requestUri]
        controller?.let {
            controller.process(req, resp)
        } ?: run {
            resp.status = HttpServletResponse.SC_NOT_FOUND
            return
        }
    }
}