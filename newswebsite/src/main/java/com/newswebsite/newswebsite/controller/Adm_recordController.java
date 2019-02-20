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
@RequestMapping("/adminrecord")
public class Adm_recordController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserRecordService userRecordService;
    @Autowired
    private Adm_recordService adm_recordService;

    int  id = 0;
    String time = "";
    int find = 0;
    int pagePrevious = 0;
    int pageNext = 0;
    String pageNum;

    @GetMapping
    public String index(Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        if(find == 1){
            find = 0;
            List<Adm_record> user = adm_recordService.queryByID(id);
            model.addAttribute("adm_record",user);
            model.addAttribute("colNum", user.size());
            model.addAttribute("loginName", UserController.loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else if(find == 2){
            find = 0;
            List<Adm_record> user = adm_recordService.findByTime(time);
            model.addAttribute("adm_record",user);
            model.addAttribute("colNum",user.size());
            model.addAttribute("loginName", UserController.loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else if(pagePrevious == 1){
            pagePrevious = 0;
            id = Integer.parseInt(pageNum) - 1;
            id *= 10;

            long colNum = adm_recordService.getColNum();
            model.addAttribute("colNum",colNum);
            model.addAttribute("loginName", UserController.loginName);

            List<Adm_record> list = adm_recordService.page(id, 10);
            model.addAttribute("adm_record",list);

            model.addAttribute("page",id/10+1);
        }
        else if(pageNext == 1){
            pageNext = 0;
            id = Integer.parseInt(pageNum) - 1;
            id *= 10;

            long colNum = adm_recordService.getColNum();
            model.addAttribute("colNum",colNum);
            model.addAttribute("loginName", UserController.loginName);

            List<Adm_record> list = adm_recordService.page(id, 10);
            model.addAttribute("adm_record",list);

            model.addAttribute("page",id/10+1);
        }
        else {
            long colNum = adm_recordService.getColNum();
            model.addAttribute("colNum",colNum);

            List<Adm_record> list = adm_recordService.page(id, 10);
            model.addAttribute("adm_record", list);

            model.addAttribute("page",id/10+1);
            model.addAttribute("loginName", UserController.loginName);
        }

        return "adminrecord";
    }

    @PostMapping("/pageNext")
    public String pageNext(@RequestParam("pageNum") String prePageNum, Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        pageNext = 1;
        pageNum = prePageNum;

        return "redirect:/adminrecord";
    }

    @PostMapping("/pagePrevious")
    public String pagePrevious(@RequestParam("pageNum") String prePageNum, Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        pageNum = prePageNum;
        pagePrevious = 1;

        return "redirect:/adminrecord";
    }

    @PostMapping("/find")
    public String findUser(@RequestParam(value = "adminID", defaultValue = "0") int findId, @RequestParam(value = "time",defaultValue = "") String findTime, Model model){
        if(UserController.loginName.equals("")){
            find = 0;
            return "redirect:/";
        }

        if(findId > 0){
            find = 1;
            id = findId;
            time = findTime;

            return "redirect:/adminrecord";
        }
        if(!findTime.equals("")){
            find = 2;
            id = findId;
            time = findTime;

            return "redirect:/adminrecord";
        }
        else {
            find = 0;
            return "redirect:/adminrecord";
        }
    }
}
