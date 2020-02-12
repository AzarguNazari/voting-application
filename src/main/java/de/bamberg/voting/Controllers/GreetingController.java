package de.bamberg.voting.Controllers;

import com.google.common.collect.Lists;
import de.bamberg.voting.Model.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GreetingController {



    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting) {
        String[] split = greeting.getStudents().split(", +");
        Lists.newArrayList(split).forEach(System.out::println);
        System.out.println(greeting.getContent() + " " + greeting.getId() + " is receibved");
        return "result";
    }

}