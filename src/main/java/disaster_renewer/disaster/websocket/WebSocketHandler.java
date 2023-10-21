package disaster_renewer.disaster.websocket;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper;

    private static final Logger log = (Logger) LoggerFactory.getLogger(WebSocketHandler.class);


    private final Set<WebSocketSession> sessions = new HashSet<>();

    private final Map<Long, Set<WebSocketSession>> chatRoomSessionMap = new HashMap<>();

    public WebSocketHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    // 소켓 연결 확인
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("{} 연결됨", session.getId());
        sessions.add(session);
    }

    //소켓 통신 시 메세지의 전송을 다루는 부분
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);

        ChatMessageDto chatMessageDto = mapper.readValue(payload, ChatMessageDto.class);
        log.info("session {}", chatMessageDto.toString());

        Long chatRoomId = chatMessageDto.getChatRoomId();

        if(!chatRoomSessionMap.containsKey(chatRoomId)) {
            chatRoomSessionMap.put(chatRoomId, new HashSet<>());
        }
        Set<WebSocketSession> chatRoomSession = chatRoomSessionMap.get(chatRoomId);

        if(chatMessageDto.getMessageType().equals(ChatMessageDto.MessageType.ENTER)) {
            chatRoomSession.add(session);
        }
        if(chatRoomSession.size() >= 3) {
            removeClosedSession(chatRoomSession);
        }
        sendMessageToChatRoom(chatMessageDto, chatRoomSession);
    }

    //소켓 종료 확인
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("{} 연결 끊김", session.getId());
        sessions.remove(session);
    }

    //채팅 관련 메소드
    private void removeClosedSession(Set<WebSocketSession> chatRoomSession) {
        chatRoomSession.removeIf(sess -> !sessions.contains(sess));
    }

    private void sendMessageToChatRoom(ChatMessageDto chatMessageDto, Set<WebSocketSession> chatRoomSession) {
        chatRoomSession.parallelStream().forEach(sess -> sendMessage(sess, chatMessageDto));
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
