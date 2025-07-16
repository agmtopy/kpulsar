package org.agmtopy.client

import java.nio.ByteBuffer

class Message<T>(value: T?) {

    @Transient
    private lateinit var content: ByteBuffer

    /**
     * 静态方法
     */
    companion object {
        fun create(msg: String): Message<String> {
            val content = ByteBuffer.wrap(msg.toByteArray())
            return Message(msg).apply {
                this.content = content
            }
        }


    }
}