package org.agmtopy.future

import java.util.concurrent.CompletableFuture

/**
 * 组合多个Future
 */
fun main() {
    val future = Compose_Future()
    println("---------------------------------------")
    future.applyToEither()
}

class Compose_Future {


    /**
     *allOf(),将多个fn执行完成后,返回,执行失败抛出异常ExecutionException
     *无返回值
     *同组方法:anyOf:入参为可变数组或数组
     */
    fun allOf() {
        val allFuture = CompletableFuture.allOf(
            FutureTask.create_future(),
            FutureTask.create_future_time3s(),
            FutureTask.create_future_exception()
        )
        val result = allFuture.get()
        println("main result:$result")
    }

    /**
     *thenCombine(),fn1执行完成后执行fn2,然后将fn1结果和fn2结果同时送到BiFunction中进行处理,BiFunction执行线程默认为fn2的执行线程,执行失败抛出异常ExecutionException
     *返回值为BiFunction
     *同组方法:thenCombineAsync:指定BiFunction的执行线程池
     */
    fun thenCombine() {
        val combine = FutureTask.create_future().thenCombine(FutureTask.create_future_time3s()) { r1, r2 ->
            println("BiFunction threadName:" + Thread.currentThread().name)
            "fn1的result:$r1,fn2的result:$r2,"
        }

        val combineResult = combine.get()
        println("main thread result:$combineResult")
    }

    /**
     *thenAcceptBoth(),fn1执行完成后执行fn2,然后将fn1结果和fn2结果同时送到BiConsumer中进行处理,BiFunction执行线程默认为fn2的执行线程,执行失败抛出异常ExecutionException
     *无返回值
     *同组方法:thenAcceptBothAsync:指定BiFunction的执行参数
     */
    fun thenAcceptBoth() {
        val combine = FutureTask.create_future().thenAcceptBoth(FutureTask.create_future_time3s()) { r1, r2 ->
            println("BiFunction threadName:" + Thread.currentThread().name + ",fn1的result:$r1,fn2的result:$r2,")
        }

        val thenAcceptBothResult = combine.get()
        println("main thread result:$thenAcceptBothResult")
    }

    /**
     *runAfterBoth(),fn1执行完成后执行fn2,然后执行Runnable执行线程默认为fn2的执行线程,执行失败抛出异常ExecutionException
     *无返回值
     *同组方法:runAfterBothAsync:指定Runnable的执行参数
     */
    fun runAfterBoth() {
        val combine = FutureTask.create_future().runAfterBoth(FutureTask.create_future_time3s()) {
            println("Runnable threadName:" + Thread.currentThread().name)
        }

        val thenAcceptBothResult = combine.get()
        println("main thread result:$thenAcceptBothResult")
    }

    /**
     *applyToEither(),fn1或fn2执行完成,执行fn3执行线程默认为fn2的执行线程,执行失败抛出异常ExecutionException
     *有返回值,fn3的返回值
     *同组方法:applyToEitherAsync:指定Runnable的执行参数
     */
    fun applyToEither() {
        val combine = FutureTask.create_future().applyToEither(FutureTask.create_future_time3s()) { r1 ->
            println("Runnable threadName:" + Thread.currentThread().name + ",fn1或fn2执行结果为$r1")
            r1
        }

        val thenAcceptBothResult = combine.get()
        println("main thread result:$thenAcceptBothResult")
    }

    /**
     *acceptEither(),fn1或fn2执行完成,执行fn3执行线程默认为fn2的执行线程,执行失败抛出异常ExecutionException
     *无返回值
     *同组方法:acceptEitherAsync:指定Runnable的执行参数
     */
    fun acceptEither() {
        val combine = FutureTask.create_future().acceptEither(FutureTask.create_future_time3s()) { r1 ->
            println("Runnable threadName:" + Thread.currentThread().name + ",fn1或fn2执行结果为$r1")
        }

        val thenAcceptBothResult = combine.get()
        println("main thread result:$thenAcceptBothResult")
    }
    /**
     *runAfterEither(),fn1或fn2执行完成,执行runAble执行线程默认为fn2的执行线程,执行失败抛出异常ExecutionException
     *无返回值
     *同组方法:runAfterEitherAsync:指定Runnable的执行参数
     */
    fun runAfterEither() {
        val combine = FutureTask.create_future().runAfterEither(FutureTask.create_future_time3s()) {
            println("Runnable threadName:" + Thread.currentThread().name)
        }

        val thenAcceptBothResult = combine.get()
        println("main thread result:$thenAcceptBothResult")
    }


}
