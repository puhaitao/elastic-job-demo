package com.pht.ext

import org.slf4j.Logger
import sun.rmi.runtime.Log
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Logger.extInfo(format:String,arg:Any){
    //add to databases
    println("ADD LOG INFO TO DATABASE")
    //print log
    this.info(format,arg)
}
fun Logger.extInfo(msg:String){

    println(msg)
    this.extInfo(msg)
}