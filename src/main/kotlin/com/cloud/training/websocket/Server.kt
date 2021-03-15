package com.cloud.training.websocket

import com.cloud.training.functions.terminal.exec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.*
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.*
import kotlin.collections.HashSet

private class Message(val msgType: MessageType, val data: Any)
private enum class MessageType { VIEW, DATABASE, JOIN }

class ChatHandler : TextWebSocketHandler() {

    private val scope = CoroutineScope(Dispatchers.Default)
    private val sessions = Collections.synchronizedSet(HashSet<WebSocketSession>())

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        session.close()
        scope.coroutineContext.cancelChildren()
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = ObjectMapper().readTree(message.payload)
        when (payload.asEnum("type")) {
            MessageType.VIEW -> {
                run(session)
            }
            MessageType.JOIN -> {
                sessions.add(session)
            }
        }
    }

    private fun run(session: WebSocketSession) {
        scope.runCatching {
            while (isActive) {
                emit(session, Message(MessageType.VIEW, exec().toString()))
                println("coroutine running")
            }
        }.onFailure {
            it.printStackTrace()
        }
    }

    private fun emit(
            session: WebSocketSession,
            msg: Message
    ) {
        session.sendMessage(TextMessage(jacksonObjectMapper().writeValueAsString(msg)))
    }

    private fun JsonNode.asEnum(fieldName: String): MessageType {
        return MessageType.valueOf(this.get(fieldName).asText())
    }

}
