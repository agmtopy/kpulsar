package org.agmtopy.client

/**
 * 消息类型生成器
 */
class TypedMessageBuilderImpl<T>(value: T, producerImpl: ProducerImpl) {

    //需要发送的内容
    @Transient
    private val value: T? = value


    //消息发送器
    private val producerImpl: ProducerImpl = producerImpl

    /**
     * 发送消息
     */
    fun send(): String {
        try {
            this.sendAsync()
            return producerImpl.triggerFlush()
        } catch (e: Exception) {
            throw RuntimeException("send fail,error message:" + e.message, e)
        }
    }

    /**
     * 将消息刷新到缓冲区
     */
    private fun sendAsync() {
        var message = this.getMessage()
        producerImpl.internalSendAsync(message)
    }

    private fun getMessage(): Message<T> {
        return Message(value)
    }

}