package org.agmtopy.future

import java.util.concurrent.TimeUnit

fun main() {
    val future = Get_Future_Result()
    future.isDone()
}

class Get_Future_Result {

    /**
     * get()获取结果,阻塞的方式,不支持lambda方式进行调用
     * 抛出异常:ExecutionException\InterruptedException
     * 同组方法: get(long timeout, TimeUnit unit)
     */
    fun get() {
        val future = FutureTask.create_future()
        val result0 = future.get()

        val future_exception = FutureTask.create_future_exception()
        val result1 = future_exception.get()
    }

    /**
     * join()获取结果,阻塞的方式,支持lambda方式进行调用
     * 抛出异常:CompletionException
     * 同组方法:无
     */
    fun join() {
        val future = FutureTask.create_future()
        var list = ArrayList<String>()
        list.add("1")

        list.forEach { item ->
            val result0 = future.join()
            println("result0:$result0,item:$item")
        }


        val future1 = FutureTask.create_future_exception()
        future1.join()
    }

    /**
     * getNow()立即尝试获取结果,如果future未完成就返回默认值,非阻塞的方式,支持lambda方式进行调用
     * 抛出异常:CompletionException
     * 同组方法:无
     */
    fun getNow() {
        val future = FutureTask.create_future_time3s()
        val result = future.getNow("defaultValue")
        println("create_future_time3s result:$result")

        val future1 = FutureTask.create_future_exception()
        TimeUnit.SECONDS.sleep(1)
        future1.getNow("exception")
    }

    fun isDone(){
        val future = FutureTask.create_future_time3s()
        val done = future.isDone
        println("future是否完成:$done")
    }

}
