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
@RequestMapping("/tags")
public class TagController {
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
    String pageNum;
    int pagePrevious = 0;
    int pageNext = 0;
    String name;

    @GetMapping
    public String index(Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        if(find == 1){
            find = 0;
            List<Tags> user = tagService.queryByID(id);
            model.addAttribute("tags",user);
            model.addAttribute("colNum", user.size());
            model.addAttribute("loginName", UserController.loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else if(find == 2){
            find = 0;
            List<Tags> user = tagService.findByName(name);
            model.addAttribute("tags",user);
            model.addAttribute("colNum",user.size());
            model.addAttribute("loginName", UserController.loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else if(pageNext == 1){
            pageNext = 0;
            id = Integer.parseInt(pageNum) - 1;
            id *= 10;

            long colNum = tagService.getColNum();
            model.addAttribute("colNum",colNum);
            model.addAttribute("loginName", UserController.loginName);

            List<Tags> list = tagService.page(id, 10);
            model.addAttribute("tags",list);

            model.addAttribute("page",id/10+1);
        }
        else if(pagePrevious == 1){
            pagePrevious = 0;
            id = Integer.parseInt(pageNum) - 1;
            id *= 10;

            long colNum = tagService.getColNum();
            model.addAttribute("colNum",colNum);
            model.addAttribute("loginName", UserController.loginName);

            List<Tags> list = tagService.page(id, 10);
            model.addAttribute("tags",list);

            model.addAttribute("page",id/10+1);
        }
        else{
            List<Tags> list = tagService.page(id, 10);
            model.addAttribute("tags",list);

            long colNum = tagService.getColNum();
            model.addAttribute("colNum", colNum);

            model.addAttribute("page",id/10+1);
            model.addAttribute("loginName", UserController.loginName);
        }

        return "tag";
    }

    @PostMapping("/add")
    public String addTags(@RequestParam("name") String name){
        long colNum = tagService.getColNum();
        tagService.addTags((int)(colNum + 1), name);

        colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "add to Tag");

        return "redirect:/tags";
    }

    @PostMapping("/update")
    public String updateNews(@RequestParam("id") int id, @RequestParam("name") String name){
        tagService.updateTags(id, name);

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "update to Tag");

        return "redirect:/tags";
    }

    @PostMapping("/find")
    public String findUser(@RequestParam(value = "id", defaultValue = "0") int findId, @RequestParam(value = "name",defaultValue = "") String findName, Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        if(findId > 0){
            find = 1;
            id = findId;

            return "redirect:/tags";
        }
        if(!findName.equals("")){
            find = 2;
            name = findName;

            return "redirect:/tags";
        }
        else {
            return "redirect:/tags";
        }
    }

    @PostMapping("/pageNext")
    public String pageNext(@RequestParam("pageNum") String num, Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        pageNext = 1;
        pageNum = num;

        return "redirect:/tags";
    }

    @PostMapping("/pagePrevious")
    public String pagePrevious(@RequestParam("pageNum") String num, Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        pagePrevious = 1;
        pageNum = num;

        return "redirect:/tags";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id){
        Optional<Tags> tags = tagService.findByID(id);
        tagService.deleteTags(id);
        tagService.updateDelete(id);

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "delete to Tag");

        return "redirect:/tags";
    }
}
