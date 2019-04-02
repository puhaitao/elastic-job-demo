package com.pht.jobs

import com.dangdang.ddframe.job.api.ShardingContext
import com.dangdang.ddframe.job.api.simple.SimpleJob
import com.pht.ext.extInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.text.SimpleDateFormat
import java.util.*

class DemoJob:SimpleJob {
    val logger:Logger = LoggerFactory.getLogger(DemoJob::class.java)

    override fun execute(context: ShardingContext?) {

        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
        when (context?.getShardingItem()) {
            0 -> {
                logger.extInfo("${date} : do something by sharding item 0")
            }
            1 -> {
                logger.extInfo("${date} : do something by sharding item 1")
            }
            2 -> {
                logger.extInfo("${date} : do something by sharding item 2")
            }
            else->{
                logger.extInfo("${date} : do something by sharding item ${context?.getShardingItem()}")
            }
        }

    }

}