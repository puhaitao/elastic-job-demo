package com.pht.dao

import com.pht.beans.User

class UserDao {

    companion object {
        lateinit var list:MutableList<User>

        init {
            init()
        }

        fun init():MutableList<User>{
            val user0 = User("武松","1022","wusong@163.com","1888888888",100)
            val user1 = User("武大","1020","wuda@163.com","1888888887",100)
            val user2 = User("林冲","1022","linchong@163.com","1888888888",100)
            val user3 = User("罗成","598","luocheng@163.com","1888888887",100)
            val user4 = User("秦琼","588","qinqiong@163.com","1888888888",100)
            val user5 = User("关羽","198","guanyu@163.com","1888888887",100)
            val user6 = User("宋江","1021","songjiang@163.com","1888888888",100)
            val user7 = User("宋清","1025","songqing@163.com","1888888887",100)
            list= mutableListOf<User>(user0,user1,user2,user3,user4,user5,user6,user7)
            return list
        }
    }

    fun getUserList(count:Int?):MutableList<User>{
        try {
            val orgList = UserDao.list

            val newList = mutableListOf<User>()

            orgList.forEach {

                val status = it.userStatus

                if (status==100){
                    newList.add(it)
                }

            }

            if (newList.size>= count!!){
                return newList.subList(0, count!!)
            }else{
                return mutableListOf();
            }

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    fun updateUser(user: User){
        user.userStatus=101
    }

}