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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private Adm_recordService adm_recordService;

    @Autowired
    private UserRecordService userRecordService;

    @Autowired
    private TagService tagService;

    private int id = 0;
    public static String loginName = "";
    public static int loginID;

    int find = 0;
    int pagePrevious = 0;
    int pageNext = 0;
    String pageNum;

    String name;
    String phone;

    @GetMapping
    public String indexGet(Model model){
        if(loginName.equals("")){
            return "redirect:/";
        }

        if(pageNext == 1){
            pageNext = 0;
            id = Integer.parseInt(pageNum) - 1;
            id *= 10;

            long colNum = userService.getColNum();
            model.addAttribute("colNum",colNum);
            model.addAttribute("loginName",loginName);

            List<User> list = userService.page(id, 10);
            model.addAttribute("user",list);

            model.addAttribute("page",id/10+1);
        }
        else if(pagePrevious == 1){
            pagePrevious = 0;
            id = Integer.parseInt(pageNum) - 1;
            id *= 10;

            long colNum = userService.getColNum();
            model.addAttribute("colNum",colNum);
            model.addAttribute("loginName",loginName);

            List<User> list = userService.page(id, 10);
            model.addAttribute("user",list);

            model.addAttribute("page",id/10+1);
        }
        else if(find == 1){
            find = 0;
            List<User> user = userService.queryByID(id);
            model.addAttribute("user",user);
            model.addAttribute("colNum",user.size());
            model.addAttribute("loginName",loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else if(find == 2){
            find = 0;
            List<User> user = userService.findByName(name);
            model.addAttribute("user",user);
            model.addAttribute("colNum",user.size());
            model.addAttribute("loginName",loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else if(find == 3){
            find = 0;
            List<User> user = userService.findByPhone(phone);
            model.addAttribute("user",user);
            model.addAttribute("colNum",user.size());
            model.addAttribute("loginName",loginName);
            id = 0;
            model.addAttribute("page",id/10+1);
        }
        else{
            long colNum = userService.getColNum();
            model.addAttribute("colNum",colNum);

            List<User> list = userService.page(id, 10);
            model.addAttribute("user",list);

            model.addAttribute("page",id/10+1);
            model.addAttribute("loginName",loginName);
        }

        return "user";
    }

    @PostMapping
    public String index(@RequestParam(value = "username", defaultValue = "null") String name, @RequestParam(value = "password", defaultValue = "null") String password, Model model){
        List<Administrator> adm = userService.login(name, password);

        if(adm.size() == 0){
            return "redirect:/";
        }

        loginID = adm.get(0).getAdmID();

        model.addAttribute("loginName",adm.get(0).getName());
        loginName = adm.get(0).getName();

        long colNum = userService.getColNum();
        model.addAttribute("colNum",colNum);
        model.addAttribute("loginName",loginName);

        List<User> list = userService.page(id, 10);
        model.addAttribute("user",list);

        model.addAttribute("page",id/10+1);

        return "user";
    }

    @PostMapping("/pageNext")
    public String pageNext(@RequestParam("pageNum") String num, Model model){
        if(loginName.equals("")){
            return "redirect:/";
        }

        pageNext = 1;
        pageNum = num;

        return "redirect:/user";
    }

    @PostMapping("/pagePrevious")
    public String pagePrevious(@RequestParam("pageNum") String num, Model model){
        if(loginName.equals("")){
            return "redirect:/";
        }

        pagePrevious = 1;
        pageNum = num;

        return "redirect:/user";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("password") String password){
        if(name.equals("")){
            return "redirect:/user";
        }

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "insert to User");

        colNum = userService.getColNum();
        userService.addUser((int)(colNum + 1), name, phone, password);
        return "redirect:/user";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("password") String password){
        userService.updateUser(id, name, phone, password);

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), UserController.loginID, datetime, "update to User");

        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id){
        Optional<User> user = userService.findByID(id);
        userService.deleteUser(id);
        userService.updateDelete(id);

        long colNum = adm_recordService.getColNum();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        adm_recordService.addAdmRecord((int)(colNum + 1), loginID, datetime, "delete to User");

        return "redirect:/user";
    }

    @PostMapping("/find")
    public String findUser(@RequestParam(value = "id", defaultValue = "0") int findId, @RequestParam(value = "name",defaultValue = "") String findName, @RequestParam(value = "phone",defaultValue = "") String findPhone, Model model){
        if(loginName.equals("")){
            return "redirect:/";
        }

        if(findId > 0){
            find = 1;
            id = findId;

            return "redirect:/user";
        }

        if(!findName.equals("")){
            find = 2;
            name = findName;

            return "redirect:/user";
        }
        else if(!findPhone.equals("")){
            find = 3;
            phone = findPhone;

            return "redirect:/user";
        }
        else {
            return "redirect:/user";
        }
    }
}
