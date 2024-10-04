package Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MainPageController {

    @GetMapping("/main")
    public String boardListForm() {
        return "board-list";
    }




}
