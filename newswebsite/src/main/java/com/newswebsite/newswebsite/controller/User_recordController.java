package com.newswebsite.newswebsite.controller;

import com.newswebsite.newswebsite.bean.*;
import com.newswebsite.newswebsite.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/userrecord")
public class User_recordController {
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
    int find = 0;
    int pagePrevious = 0;
    int pageNext = 0;
    String pageNum;
    String name;
    int newsid;
    String time;

    @GetMapping
    public String index(Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        if(pageNext == 1){
            pageNext = 0;
            id = Integer.parseInt(pageNum) - 1;
            id *= 10;

            long colNum = userRecordService.getColumn();
            model.addAttribute("colNum",colNum);
            model.addAttribute("loginName", UserController.loginName);

            List<UserRecord> list = userRecordService.page(id, 10);
            model.addAttribute("user_record",list);

            model.addAttribute("page",id/10+1);
        }
        else if(pagePrevious == 1){
            pagePrevious = 0;
            id = Integer.parseInt(pageNum) - 1;
            id *= 10;

            long colNum = userRecordService.getColumn();
            model.addAttribute("colNum",colNum);
            model.addAttribute("loginName", UserController.loginName);

            List<UserRecord> list = userRecordService.page(id, 10);
            model.addAttribute("user_record",list);

            model.addAttribute("page",id/10+1);
        }
        else if(find == 1){
            find = 0;
            List<UserRecord> user = userRecordService.findByName(name);
            model.addAttribute("user_record",user);
            model.addAttribute("colNum", user.size());
            model.addAttribute("loginName", UserController.loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else if(find == 2){
            find = 0;
            List<UserRecord> user = userRecordService.findByNewsID(newsid);
            model.addAttribute("user_record",user);
            model.addAttribute("colNum", user.size());
            model.addAttribute("loginName", UserController.loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else if(find == 3){
            find = 0;
            List<UserRecord> user = userRecordService.findByTime(time);
            model.addAttribute("user_record",user);
            model.addAttribute("colNum",user.size());
            model.addAttribute("loginName", UserController.loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else{
            long colNum = userRecordService.getColumn();
            model.addAttribute("colNum",colNum);

            List<UserRecord> list = userRecordService.page(id, 10);
            model.addAttribute("user_record", list);
            model.addAttribute("page",id/10+1);

            model.addAttribute("loginName", UserController.loginName);
        }

        return "userrecord";
    }

    @PostMapping("/pageNext")
    public String pageNext(@RequestParam("pageNum") String num, Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        pageNext = 1;
        pageNum = num;

        return "redirect:/userrecord";
    }

    @PostMapping("/pagePrevious")
    public String pagePrevious(@RequestParam("pageNum") String num, Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        pagePrevious = 1;
        pageNum = num;

        return "redirect:/userrecord";
    }

    @PostMapping("/find")
    public String findUser(@RequestParam(value = "name", defaultValue = "") String findName,@RequestParam(value = "newsID", defaultValue = "0") int findNewsId, @RequestParam(value = "time",defaultValue = "") String findTime, Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        if(!findName.equals("")){
            find = 1;
            name = findName;

            return "redirect:/userrecord";
        }
        if(findNewsId > 0){
            find = 2;
            newsid = findNewsId;

            return "redirect:/userrecord";
        }
        if(!findTime.equals("")){
            find = 3;
            time = findTime;

            return "redirect:/userrecord";
        }
        else {
            return "redirect:/userrecord";
        }
    }
}
