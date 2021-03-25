package com.cloud.training.websocket

import com.cloud.training.functions.terminal.exec
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.*
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.*
import kotlin.collections.HashSet

private data class Message(val msgType: MessageType, val data: Any)
private enum class MessageType { VIEW, DATABASE, JOIN }

class ChatHandler : TextWebSocketHandler() {

    private val scope = CoroutineScope(Dispatchers.Default)
    private val sessions = Collections.synchronizedSet(HashSet<WebSocketSession>())

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        session.close()
        sessions.remove(session)
        scope.coroutineContext.cancelChildren()
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
        run(session)
    }

    private fun run(session: WebSocketSession) {
        scope.runCatching {
            while (isActive) {
                val executeResult = exec().toString()
                println(executeResult)
                createMessage(executeResult).also { session.sendMessage(it) }
                runBlocking { delay(1000) }
            }
        }.onFailure {
            it.printStackTrace()
        }
    }

    private fun createMessage(msg: String): TextMessage {
        val message = Message(MessageType.VIEW, msg)
        return TextMessage(
                jacksonObjectMapper().writeValueAsString(message)
        )
    }

}