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
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(100); // simulated delay

        logic.getReady();

        if (message.getName().equals("X")) {
            return new Greeting("Movement = " + HtmlUtils.htmlEscape(message.getName()) + logic.printBoard());
        }
        if (message.getName().equals("O")) {
            return new Greeting("Movement = " + HtmlUtils.htmlEscape(message.getName()) + "!");
        } else {
            return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        }
    }

    public void game() {

        logic.getReady();
        logic.newCode();
    }


}

