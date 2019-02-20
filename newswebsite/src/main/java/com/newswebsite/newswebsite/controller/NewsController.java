package com.newswebsite.newswebsite.controller;

import com.newswebsite.newswebsite.bean.*;
import com.newswebsite.newswebsite.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @Autowired
    private Adm_recordService adm_recordService;
    @Autowired
    private UserRecordService userRecordService;

    int id = 0;
    String time;
    String title;
    String pageNum;
    int find = 0;
    int pagePrevious = 0;
    int pageNext = 0;

    @GetMapping
    public String index(Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        if(pageNext == 1){
            pageNext = 0;
            id = Integer.parseInt(pageNum) - 1;
            id *= 10;

            long colNum = newsService.getColNum();
            model.addAttribute("colNum",colNum);
            model.addAttribute("loginName", UserController.loginName);

            List<News> list = newsService.page(id, 10);
            model.addAttribute("news",list);

            model.addAttribute("page",id/10+1);
        }
        else if(pagePrevious == 1){
            pagePrevious = 0;
            id = Integer.parseInt(pageNum) - 1;
            id *= 10;

            long colNum = newsService.getColNum();
            model.addAttribute("colNum",colNum);
            model.addAttribute("loginName", UserController.loginName);

            List<News> list = newsService.page(id, 10);
            model.addAttribute("news",list);

            model.addAttribute("page",id/10+1);
        }
        else if(find == 1){
            find = 0;
            List<News> user = newsService.queryByID(id);
            model.addAttribute("news",user);
            model.addAttribute("colNum", user.size());
            model.addAttribute("loginName", UserController.loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else if(find == 2){
            find = 0;
            List<News> user = newsService.findByTime(time);
            model.addAttribute("news",user);
            model.addAttribute("colNum",user.size());
            model.addAttribute("loginName", UserController.loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else if(find == 3){
            find = 0;
            List<News> user = newsService.findByTitle(title);
            model.addAttribute("news",user);
            model.addAttribute("colNum",user.size());
            model.addAttribute("loginName", UserController.loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else{
            List<News> list = newsService.page(id, 10);
            model.addAttribute("news",list);

            long colNum = newsService.getColNum();
            model.addAttribute("colNum", colNum);

            model.addAttribute("page",id/10+1);
            model.addAttribute("loginName", UserController.loginName);
        }

        return "news";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("time") String time, @RequestParam("title") String title,
                          @RequestParam("summary") String summary, @RequestParam("news_address") String news_address,
                          @RequestParam("img_address") String img_address, @RequestParam("heat") int heat, @RequestParam("tag") int tagID){
        long colNum = newsService.getColNum();
        long via = colNum;
        List<News> variable = newsService.queryByID((int)via);
        while(variable.size() != 0){
            via++;
            variable = newsService.queryByID((int)via);
        }

        newsService.addNews((int)via, time, title, summary, news_address, img_address, heat, tagID);

        colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "insert to News");

        return "redirect:/news";
    }

    @PostMapping("/update")
    public String updateNews(@RequestParam("id") int id, @RequestParam("time") String time, @RequestParam("title") String title,
                          @RequestParam("summary") String summary, @RequestParam("news_address") String news_address,
                          @RequestParam("img_address") String img_address, @RequestParam("heat") int heat, @RequestParam("tag") int tagID){
        newsService.updateNews(id, time, title, summary, news_address, img_address, heat, tagID);

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "update to News");

        return "redirect:/news";
    }

    @PostMapping("/pageNext")
    public String pageNext(@RequestParam("pageNum") String prePageNum, Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        pageNext = 1;
        pageNum = prePageNum;

        return "redirect:/news";
    }

    @PostMapping("/pagePrevious")
    public String pagePrevious(@RequestParam("pageNum") String prePageNum, Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        pagePrevious = 1;
        pageNum = prePageNum;

        return "redirect:/news";
    }

    @PostMapping("/find")
    public String findUser(@RequestParam(value = "newsID", defaultValue = "0") int newsId, @RequestParam(value = "time",defaultValue = "") String newsTime, @RequestParam(value = "title",defaultValue = "") String newsTitle, Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        if(newsId > 0){
            find = 1;
            id = newsId;

            return "redirect:/news";
        }
        if(!newsTime.equals("")){
            find = 2;
            time = newsTime;

            return "redirect:/news";
        }
        else if(!newsTitle.equals("")){
            find = 3;
            title = newsTitle;

            return "redirect:/news";
        }
        else {
            return "redirect:/news";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id){
        Optional<News> news = newsService.findByID(id);
        newsService.deleteNews(id);
        newsService.updateDelete(id);

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "delete to News");

        return "redirect:/news";
    }
}
