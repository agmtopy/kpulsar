package org.agmtopy.future

fun main() {
    val future = Completion_Stage()
    future.whenComplete()
    println("---------------------------------------")
    future.handle()
}


class Completion_Stage {

    /**
     * thenApply()将fn的结果传递到fn2中,执行线程为同一个线程,类似在fn执行完成后继续执行fn2
     * 有返回值
     * 同组方法:thenApplyAsync:异步执行或指定线程池异步执行
     */
    fun thenApply() {
        val fn = FutureTask.create_future()
        //将fn的结果传递到consumer_future函数中
        val fn2 = fn.thenApply { fn_result -> FutureTask.consumer_future(fn_result) }
        fn2.get()
    }

    /**
     * thenAccept()将fn的结果传递到fn2中,执行线程为同一个线程,类似在fn执行完成后继续执行fn2
     * 无返回值,get()结果为null
     * 关心上一步的结果
     * 同组方法:thenAcceptAsync:异步执行或指定线程池异步执行
     */
    fun thenAccept() {
        val fn = FutureTask.create_future()
        val fn2 = fn.thenAccept { fn_result -> FutureTask.consumer_future(fn_result) }

        val fn2Result = fn2.get()
        println("fn2Result:$fn2Result");
    }

    /**
     * thenRun()将fn执行完成后执行Runnable,执行线程为同一个线程
     * 无返回值,get()结果为null
     * 不关心上一步的结果
     * 同组方法:thenRunAsync:异步执行或指定线程池异步执行
     */
    fun thenRun() {
        val fn = FutureTask.create_future()
        val fn2 = fn.thenRun() { println("fn_result:" + Thread.currentThread().name) }

        val fn2Result = fn2.get()
        println("fn2Result:$fn2Result");
    }


    /**
     * thenCompose()将fn执行完成后执行fn2,执行线程为同一个线程(存疑)
     * 有返回值,且是fn2的返回值
     * 关心上一步的结果,因为fn2的结果的类型也和fn必须保持一致
     * 同组方法:thenComposeAsync:异步执行或指定线程池异步执行,fn2会提交到线程池中去
     */
    fun thenCompose() {
        val fn = FutureTask.create_future()
        val fn2 = fn.thenCompose { fn_result -> FutureTask.consumer_future(fn_result) }

        val fn2Result = fn2.get()
        println("fn2Result:$fn2Result");
    }

    fun thenComposeAsync() {
        val fn = FutureTask.create_future()
        val fn2 = fn.thenComposeAsync { fn_result -> FutureTask.consumer_future(fn_result) }

        val fn2Result = fn2.get()
        println("fn2Result:$fn2Result");
    }

    /**
     * exceptionally()处理fn执行异常,执行线程为main线程
     * 有返回值,且是fn2的返回值
     * 关心上一步的异常
     * 同组方法:无
     */
    fun exceptionally() {
        val fn = FutureTask.create_future_exception()
        val fn2 = fn.exceptionally { ex ->
            var threadName = Thread.currentThread().name
            println("thread_name:$threadName,异常堆栈为:" + ex.message)
            "返回错误信息"
        }

        val fn2Result = fn2.get()
        println("fn2Result:$fn2Result");
    }

    /**
     * whenComplete()处理fn返回的结果或者执行异常,执行线程为main线程
     * 有返回值,且是fn的返回值,whenComplete没有返回值
     * 关心上一步的异常
     * 同组方法:whenCompleteAsync:异步执行或指定线程池异步执行,fn2会提交到线程池中去
     */
    fun whenComplete() {
        val fn = FutureTask.create_future()
//        val fn = FutureTask.create_future_exception()
        val fn2 = fn.whenComplete { fn_result, ex ->
            var threadName = Thread.currentThread().name

            if (ex == null) {
                println("thread_name:$threadName,fn执行成功,返回结果为:$fn_result")
            }

            if (ex != null) {
                println("thread_name:$threadName,fn执行失败,异常堆栈为:" + ex.message)
            }
        }

        val fn2Result = fn2.get()
        println("fn2Result:$fn2Result");
    }


    /**
     * handle()处理fn返回的结果或者执行异常,执行线程为main线程
     * 有返回值,且是fn2的返回值,不是fn的返回值
     * 关心上一步的异常
     * 同组方法:handleAsync:异步执行或指定线程池异步执行,fn2会提交到线程池中去
     */
    fun handle() {
        val fn = FutureTask.create_future()
//        val fn = FutureTask.create_future_exception()
        val fn2 = fn.handle { fn_result, ex ->
            var threadName = Thread.currentThread().name

            if (ex == null) {
                println("thread_name:$threadName,fn执行成功,返回结果为:$fn_result")
            }

            if (ex != null) {
                println("thread_name:$threadName,fn执行失败,异常堆栈为:" + ex.message)
            }
            return@handle "handle执行完成"
        }

        val fn2Result = fn2.get()
        println("fn2Result:$fn2Result");
    }

}