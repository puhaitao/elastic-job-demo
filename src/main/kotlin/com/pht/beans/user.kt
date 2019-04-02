package com.pht.beans

import com.alibaba.fastjson.JSON

data class User(var userName:String,
                var userBirth:String,
                var userEmail:String,
                var userMobile:String,
                var userStatus:Int){
    override fun toString(): String {
        return JSON.toJSONString(this)
    }
}

