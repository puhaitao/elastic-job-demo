package com.pht

import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration
import com.dangdang.ddframe.job.config.JobCoreConfiguration
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration
import com.dangdang.ddframe.job.executor.ShardingContexts
import com.dangdang.ddframe.job.lite.api.JobScheduler
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter
import com.pht.jobs.DemoJob
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter
import com.pht.jobs.DataFlowDemoJob


fun main(args: Array<String>) {

    setupDataFloeJobDemo()
}

fun createRegistryCenter(): CoordinatorRegistryCenter{
    val regCenter = ZookeeperRegistryCenter(ZookeeperConfiguration("148.70.53.80:2181", "elastic-job-demo"))
    regCenter.init()
    return regCenter
}

fun setupSimpleJobDemo(){
    // 定义作业核心配置
    val simpleCoreConfig = JobCoreConfiguration.newBuilder("demoSimpleJob", "0/5 * * * * ?", 3).build()
    // 定义SIMPLE类型配置
    val simpleJobConfig = SimpleJobConfiguration(simpleCoreConfig, DemoJob::class.java!!.getCanonicalName())
    // 定义Lite作业根配置
    val simpleJobRootConfig:LiteJobConfiguration= LiteJobConfiguration.newBuilder(simpleJobConfig).build()
    JobScheduler(createRegistryCenter(), simpleJobRootConfig).init();
}
fun setupDataFloeJobDemo(){
    // 定义作业核心配置
    val coreConfiguration = JobCoreConfiguration.newBuilder("demoDataFlowJob", "*/2 * * * * ?", 1)
        .description("数据流处理定时任务").jobParameter("1").failover(true)
        .shardingItemParameters("0=a,1=b,2=c").build()
    // 定义SIMPLE类型配置
    val dataflowJobConfiguration = DataflowJobConfiguration(coreConfiguration, DataFlowDemoJob::class.java!!.getCanonicalName(),true)
    // 定义Lite作业根配置
    val liteJobConfiguration:LiteJobConfiguration= LiteJobConfiguration.newBuilder(dataflowJobConfiguration).build()
    JobScheduler(createRegistryCenter(), liteJobConfiguration,MyJobListener()).init();
}

class MyJobListener:ElasticJobListener{
    override fun beforeJobExecuted(p0: ShardingContexts?) {
        println("---------------")
        println("LISTENER:Before Job")
    }

    override fun afterJobExecuted(p0: ShardingContexts?) {
        println("LISTENER:After Job")
    }

}