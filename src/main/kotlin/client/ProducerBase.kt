package org.agmtopy.client


/**
 * 客户端操作类
 */
class ProducerBase<T>(serviceUrl: String, topicName: String) {

    /**
     * 发送指定对象
     */
    fun send(msg: T):String {
        //1. 将发送消息委托给org.apache.pulsar.client.api.TypedMessageBuilder
        val messageBuilderImpl = TypedMessageBuilderImpl<T>()
        messageBuilderImpl.send()

        return "msgId"
    }

}