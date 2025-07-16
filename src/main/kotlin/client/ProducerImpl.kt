package org.agmtopy.client

import java.util.concurrent.CompletableFuture

/**
 * producer实现
 */
class ProducerImpl {

    private var messages = ArrayList<Message<*>>()


    /**
     * 将缓冲区发送到服务器
     */
    fun triggerFlush():String {



        return "";
    }


    /**
     * 将消息刷新到缓冲区
     */
    fun <T> internalSendAsync(message: Message<T>) {
        messages.add(message)
    }
}