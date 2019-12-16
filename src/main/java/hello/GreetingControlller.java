package hello;

import domain.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

@Controller
public class GreetingControlller {

    Logic logic = new Logic();

    @MessageMapping("/hello")
    @SendTo("/tictactoe/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(100); // simulated delay

        if (logic.getIsReady()) {
            return makeMove(message);
        } else {
            logic.getReady();
            return makeMove(message);
        }

    }

    public Greeting makeMove(HelloMessage message) {
//        if (message.getName().equals("X")) {
//            return new Greeting("Movement = " + HtmlUtils.htmlEscape(message.getName()) + logic.printBoard());
//        }
//        if (message.getName().equals("O")) {
//            return new Greeting("Movement = " + HtmlUtils.htmlEscape(message.getName()) + "!");
//        } else {
//            return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//        }
        //int numinput = Integer.parseInt(message.getName());
        return new Greeting("Your move is " + HtmlUtils.htmlEscape(logic.turnHumanPlayer(message.getName())));
    }

    public void game() {

        logic.getReady();
        logic.newCode();
    }


}

