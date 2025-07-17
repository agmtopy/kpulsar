package org.agmtopy.future

import java.util.concurrent.CompletableFuture


/**
 * 创建Future
 */
fun main() {
    val future = Create_Future()
    future.completedFuture()
}

/**
 * 创建future的几种方式
 */
class Create_Future {


    /**
     * supplyAsync提交任务,使用ForkJoinPool.commonPool异步执行
     * 带返回结果
     * 同组方法: supplyAsync(Runnable runnable, Executor executor)
     */
    fun supplyAsync() {
        val future = CompletableFuture.supplyAsync {
            return@supplyAsync FutureTask.printlnWithException()
        }
        val result = future.get();
        println("main thread result:$result")
    }

    /**
     * runAsync提交任务,使用ForkJoinPool.commonPool异步执行
     * 无返回结果
     * 同组方法: runAsync(Runnable runnable, Executor executor)
     */
    fun runAsync() {
        val future = CompletableFuture.runAsync {
//            return@runAsync printlnWithString()
            FutureTask.printlnWithException()
        }
        val result = future.get();
        //返回结果为null
        println("main thread result:$result")
    }

    /**
     * 返回设定值,不会启用线程池去提交任务
     */
    fun completedFuture() {
        val future = CompletableFuture.completedFuture {
            return@completedFuture FutureTask.printlnWithException()
        }
        val result = future.get();
        //返回结果为null
        println("main thread result:$result")
    }


}