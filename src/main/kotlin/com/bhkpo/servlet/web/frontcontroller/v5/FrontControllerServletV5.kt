package com.bhkpo.servlet.web.frontcontroller.v5

import com.bhkpo.servlet.web.frontcontroller.ModelView
import com.bhkpo.servlet.web.frontcontroller.MyView
import com.bhkpo.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3
import com.bhkpo.servlet.web.frontcontroller.v3.controller.MemberListControllerV3
import com.bhkpo.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3
import com.bhkpo.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4
import com.bhkpo.servlet.web.frontcontroller.v4.controller.MemberListControllerV4
import com.bhkpo.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4
import com.bhkpo.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter
import com.bhkpo.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "frontcontrollerServletV5", urlPatterns = ["/front-controller/v5/*"])
class FrontControllerServletV5(): HttpServlet() {
    private val handlerMappingMap = HashMap<String, Any>() 
    private val handlerAdapters = ArrayList<MyHandlerAdapter>()
    
    init {
        initHandlerMappingMap()
        initHandlerAdaptors()
    }

    private fun initHandlerMappingMap() {
        handlerMappingMap["/front-controller/v5/v3/members/new-form"] = MemberFormControllerV3()
        handlerMappingMap["/front-controller/v5/v3/members/save"] = MemberSaveControllerV3()
        handlerMappingMap["/front-controller/v5/v3/members"] = MemberListControllerV3()

        handlerMappingMap["/front-controller/v5/v4/members/new-form"] = MemberFormControllerV4()
        handlerMappingMap["/front-controller/v5/v4/members/save"] = MemberSaveControllerV4()
        handlerMappingMap["/front-controller/v5/v4/members"] = MemberListControllerV4()
    }

    private fun initHandlerAdaptors() {
        handlerAdapters.add(ControllerV3HandlerAdapter())
        handlerAdapters.add(ControllerV4HandlerAdapter())
    }

    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        val handler: Any? = getHandler(req)

        if (handler == null) {
            resp.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        val adapter: MyHandlerAdapter = getHandlerAdapter(handler)
        val mv: ModelView = adapter.handle(req, resp, handler)
        val view: MyView = viewResolver(mv.viewName)

        view.render(mv.model, req, resp)
    }

    private fun getHandlerAdapter(handler: Any): MyHandlerAdapter {
        handlerAdapters.forEach { adapter ->
            if (adapter.supports(handler)) {
                return adapter
            }
        }
        throw IllegalArgumentException("handler adapter 를 찾을 수 없습니다. handler=$handler")
    }

    private fun getHandler(request: HttpServletRequest): Any? {
        val requestUri: String = request.requestURI
        return handlerMappingMap[requestUri]
    }

    private fun viewResolver(viewName: String): MyView {
        return MyView("/WEB-INF/views/$viewName.jsp")
    }
}
