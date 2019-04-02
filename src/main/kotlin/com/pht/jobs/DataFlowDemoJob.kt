package com.pht.jobs

import com.dangdang.ddframe.job.api.ShardingContext
import com.dangdang.ddframe.job.api.dataflow.DataflowJob
import com.pht.beans.User
import com.pht.dao.UserDao
import java.util.*

class DataFlowDemoJob : DataflowJob<User> {
    val userDao=UserDao()

    override fun fetchData(context: ShardingContext?): MutableList<User> {

        val countstr =context?.jobParameter

        val count = countstr?.toInt()



        val datas =userDao.getUserList(count)
        println("fetchData at ${Date()}")

        return datas
    }

    override fun processData(context: ShardingContext?, datas: MutableList<User>?) {
        println("processData at ${Date()}")
        try {
            datas!!.forEach {
                userDao.updateUser(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}