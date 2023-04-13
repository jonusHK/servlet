package com.bhkpo.servlet.web.frontcontroller.v3

import com.bhkpo.servlet.web.frontcontroller.ModelView

interface ControllerV3 {
    fun process(paramMap: Map<String, String>): ModelView
}