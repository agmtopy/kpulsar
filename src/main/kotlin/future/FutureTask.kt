package org.agmtopy.future

import java.time.LocalTime
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

class FutureTask {
    companion object {
        fun consumer_future(result: String): CompletableFuture<String> {
            return CompletableFuture.supplyAsync {
                printlnWithResult(result)
                return@supplyAsync printlnWithString()
            }
        }


        fun create_future_exception(): CompletableFuture<String> {
            return CompletableFuture.supplyAsync {
                return@supplyAsync printlnWithException()
            }
        }


        fun create_future(): CompletableFuture<String> {
            return CompletableFuture.supplyAsync {
                return@supplyAsync printlnWithString()
            }
        }


        fun create_future_time3s(): CompletableFuture<String> {
            return CompletableFuture.supplyAsync {
                TimeUnit.SECONDS.sleep(3)
                return@supplyAsync printlnWithString()
            }
        }


        fun printlnWithVoid() {
            println("Thread Name:" + Thread.currentThread().name);
        }

        fun printlnWithString(): String {
            println("Thread Name:" + Thread.currentThread().name);
            return LocalTime.now().toString()
        }

        fun printlnWithResult(result: String) {
            println("Thread Name:$result");
        }

        fun printlnWithException(): String {
            var a = 3;
            var b = 0;
            var c = a / b;
            println("c:$c");

            println("Thread Name:" + Thread.currentThread().name);
            return LocalTime.now().toString()
        }
    }
}