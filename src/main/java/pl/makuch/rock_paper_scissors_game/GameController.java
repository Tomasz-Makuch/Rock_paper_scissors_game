package pl.makuch.rock_paper_scissors_game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.io.IOException;

@Controller
public class GameController {

    private UserRankingDao userRankingDao;

    @Autowired
    public GameController(UserRankingDao userRankingDao) {
        this.userRankingDao = userRankingDao;
    }

    @GetMapping("/")
    public String getstartForm(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "start";
    }
    @PostMapping("/game")
    public String startGame(@ModelAttribute("user") User user){
        return "game";
    }

    @GetMapping("/ranking")
    public String showRanking(Model model) throws IOException {
        userRankingDao.readRankingFile();
        model.addAttribute("header", userRankingDao.getHeaders());
        model.addAttribute("results", userRankingDao.getUsersResults());
        return "ranking";
    }

    @PostMapping("/save")
    public String saveScore(User user) throws IOException {
        userRankingDao.saveResult(user);
        return "redirect:/";
    }




}
