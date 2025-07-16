package org.agmtopy.future

fun main() {
    val future = Completion_Stage()
    future.thenRun()
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
     * 同组方法:thenRunAsync:异步执行或指定线程池异步执行
     */
    fun thenRun() {
        val fn = FutureTask.create_future()
        val fn2 = fn.thenRun() { println("fn_result:" + Thread.currentThread().name) }

        val fn2Result = fn2.get()
        println("fn2Result:$fn2Result");
    }

}