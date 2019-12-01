package TicTacToeGameServer;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import model.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint(value = "/greeter/")
public class GreeterEndpoint {

    private static final Logger log = LoggerFactory.getLogger(GreeterEndpoint.class);
    private static final List<Session> sessions = new ArrayList<>();

    @OnOpen
    public void onConnect(Session session) {
        log.info("Connected SessionID: {}", session.getId());

        sessions.add(session);
        log.info("Session added. Session count is {}", sessions.size());
    }

    @OnMessage
    public void onText(String message, Session session) {
        log.info("Session ID: {} Received: {}", session.getId(), message);
        handleMessageFromClient(message, session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        log.info("Session ID: {} Closed. Reason: {}", session.getId(), reason);
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        log.error("Session ID: {} Error: {}", session.getId(), cause.getMessage());
    }

    private void handleMessageFromClient(String jsonMessage, Session session) {
        Gson gson = new Gson();
        log.info("Session ID: {} Handling message: {}", session.getId(), jsonMessage);

        try {
            Greeting message = gson.fromJson(jsonMessage, Greeting.class);
            log.info("Session ID: {} Message handled: {}", session.getId(), message);
        } catch (JsonSyntaxException ex) {
            log.error("Can't process message: {0}", ex);
        }
    }

}
