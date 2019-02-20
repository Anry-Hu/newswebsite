package com.newswebsite.newswebsite.controller;


import com.newswebsite.newswebsite.bean.Statistic;
import com.newswebsite.newswebsite.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping
    public String index(Model model){
        if(UserController.loginName.equals("")){
            return "redirect:/";
        }

        List<Statistic> record = statisticService.getRecord();
        for(int i=0;i<7;i++){
            model.addAttribute("date"+Integer.toString(i),record.get(i).getDate());
            model.addAttribute("news"+Integer.toString(i),record.get(i).getNews());
            model.addAttribute("user"+Integer.toString(i),record.get(i).getUser());
        }

        model.addAttribute("loginName", UserController.loginName);

        return "statistic";
    }
}
