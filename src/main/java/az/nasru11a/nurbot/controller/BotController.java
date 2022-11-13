package az.nasru11a.nurbot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bot")
public class BotController {

    @GetMapping("/get-name")
    public String getBotName() {
        return "I'm NurBot";
    }

    public String createName(String name) {
        return "New name";
    }
}
