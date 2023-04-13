package com.bhkpo.servlet.web.frontcontroller.v4

interface ControllerV4 {

    /**
     * @param paramMap
     * @param model
     * @return viewName
     */
    fun process(paramMap: Map<String, String>, model: MutableMap<String, Any>): String
}