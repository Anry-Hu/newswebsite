package com.newswebsite.newswebsite.controller;

import com.newswebsite.newswebsite.bean.TopPic;
import com.newswebsite.newswebsite.service.Adm_recordService;
import com.newswebsite.newswebsite.service.TopPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/toppic")
public class TopPicController {
    @Autowired
    private TopPicService topPicService;
    @Autowired
    private Adm_recordService adm_recordService;

    int id = 0;
    int find = 0;
    int pagePrevious = 0;
    int pageNext = 0;
    String pageNum;
    String title;

    @GetMapping
    public String indexGet(Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        if(pageNext == 1){
            pageNext = 0;
            id = Integer.parseInt(pageNum) - 1;
            id *= 10;

            long colNum = topPicService.getColNum();
            model.addAttribute("colNum",colNum);
            model.addAttribute("loginName",UserController.loginName);

            List<TopPic> list = topPicService.page(id, 10);
            model.addAttribute("user",list);

            model.addAttribute("page",id/10+1);
        }
        else if(pagePrevious == 1){
            pagePrevious = 0;
            id = Integer.parseInt(pageNum) - 1;
            id *= 10;

            long colNum = topPicService.getColNum();
            model.addAttribute("colNum",colNum);
            model.addAttribute("loginName",UserController.loginName);

            List<TopPic> list = topPicService.page(id, 10);
            model.addAttribute("user",list);

            model.addAttribute("page",id/10+1);
        }
        else if(find == 1){
            find = 0;
            List<TopPic> user = topPicService.queryByID(id);
            model.addAttribute("user",user);
            model.addAttribute("colNum", user.size());
            model.addAttribute("loginName",UserController.loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else if(find == 2){
            find = 0;
            List<TopPic> user = topPicService.findByTitle(title);
            model.addAttribute("user",user);
            model.addAttribute("colNum",user.size());
            model.addAttribute("loginName",UserController.loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else{
            long colNum = topPicService.getColNum();
            model.addAttribute("colNum",colNum);

            List<TopPic> list = topPicService.page(id, 10);
            model.addAttribute("user",list);

            model.addAttribute("page",id/10+1);
            model.addAttribute("loginName",UserController.loginName);
        }

        return "toppic";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("password") String password){
        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "insert to TopPic");

        colNum = topPicService.getColNum();
        topPicService.addTopPic((int)(colNum + 1), name, phone, password);

        return "redirect:/toppic";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("password") String password){
        topPicService.updateTopPic(id, name, phone, password);

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "update to TopPic");

        return "redirect:/toppic";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id){
        Optional<TopPic> user = topPicService.findByID(id);
        topPicService.deleteTopPic(id);
        topPicService.updateDelete(id);

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "delete to TopPic");

        return "redirect:/toppic";
    }

    @PostMapping("/pageNext")
    public String pageNext(@RequestParam("pageNum") String num, Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        pageNext = 1;
        pageNum = num;

        return "redirect:/toppic";
    }

    @PostMapping("/pagePrevious")
    public String pagePrevious(@RequestParam("pageNum") String num, Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        pagePrevious = 1;
        pageNum = num;

        return "redirect:/toppic";
    }

    @PostMapping("/find")
    public String findUser(@RequestParam(value = "picID", defaultValue = "0") Integer findId, @RequestParam(value = "title",defaultValue = "") String findTitle,  Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        if(findId > 0){
            find = 1;
            id = findId;

            return "redirect:/toppic";
        }
        if(!findTitle.equals("")){
            find = 2;
            title = findTitle;

            return "redirect:/toppic";
        }
        else {
            return "redirect:/toppic";
        }
    }
}
