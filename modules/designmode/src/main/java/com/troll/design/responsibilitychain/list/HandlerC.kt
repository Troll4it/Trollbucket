package com.troll.design.responsibilitychain.listclass HandlerC : IHandler {    override fun process() {        println("采用数组的方式实现：C")    }    override fun interceptor(): Boolean {        return true    }}