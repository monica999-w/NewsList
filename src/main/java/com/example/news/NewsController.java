package com.example.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NewsController {
    @Autowired
    private NewsService service;


    @GetMapping("/")
    public String showNewsList(@RequestParam(required = false, defaultValue = "publicationDate_desc") String sort, Model model){
        List<News>listNews =service.listAll(sort);
        model.addAttribute("listNews",listNews);
        return "news";
    }

    @GetMapping("/add")
    public String showNewForm(Model model){
        News news = new News();
        model.addAttribute("news",new News());
        return "add-news";
    }
    @PostMapping("/news")
    public String addNews(@ModelAttribute("news") News news) {
        service.save(news);
        return "redirect:/";
    }


    @GetMapping("/search")
    public String searchNews(@RequestParam String keyword, Model model) {
        List<News> listNews = service.searchByKeyword(keyword);
        model.addAttribute("listNews", listNews);
        return "news";
    }

    @GetMapping("/version")
    @ResponseBody
    public String getProductionVersion() {
        String[] versions = {"2.5.0-dev.1", "2.4.2-5354", "2.4.2-test.675", "2.4.1-integration.1"};
        String productionVersion = getProductionVersion(versions);
        return productionVersion;
    }

    private String getProductionVersion(String[] versions) {
        String productionVersion = null;
        for (String version : versions) {
            if (!version.contains("-")) {
                productionVersion = version;
                break;
            }
            int branchIndex = version.lastIndexOf("-");
            String potentialProductionVersion = version.substring(0, branchIndex);
            if (productionVersion == null || potentialProductionVersion.compareTo(productionVersion) > 0) {
                productionVersion = potentialProductionVersion;
            }
        }
        return productionVersion;
    }
}

