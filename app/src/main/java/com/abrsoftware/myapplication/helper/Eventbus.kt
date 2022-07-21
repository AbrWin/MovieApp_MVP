package com.abrsoftware.myapplication.helper

interface Eventbus {
    fun register(subscribe: Any?)
    fun unregister(subscribe: Any?)
    fun post(event: Any?)
}