package com.example.danik.sprinttask2spring.controller;


import com.example.danik.sprinttask2spring.entities.ApplicationRequest;
import com.example.danik.sprinttask2spring.repositories.ApplicationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ApplicationRequestRepository applicationRequestRepository;

    @GetMapping(value = "/")
    public String homePage(Model model){
        List<ApplicationRequest> applicationRequestList = applicationRequestRepository.findAll();
        model.addAttribute("requests", applicationRequestList);

        return "index";
    }

    @GetMapping(value = "/new-requests")
    public String newRequest(Model model){
        List<ApplicationRequest> applicationRequestList = applicationRequestRepository.findAllByHandledIsFalse();
        model.addAttribute("requests", applicationRequestList);

        return "index";
    }

    @GetMapping(value = "/handled-requests")
    public String handledRequest(Model model){
        List<ApplicationRequest> applicationRequestList = applicationRequestRepository.findAllByHandledIsTrue();
        model.addAttribute("requests", applicationRequestList);

        return "index";
    }

    @GetMapping(value = "/add-request")
    public String addRequest(){
        return "add_request";
    }

    @PostMapping(value = "/add-request")
    public String addRequest(ApplicationRequest request){
        applicationRequestRepository.save(request);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{requestId}")
    public String detailsRequest(
            @PathVariable(name = "requestId") Long id, Model model
    ){
        ApplicationRequest applicationRequest = applicationRequestRepository.findById(id).orElse(null);
        model.addAttribute("request", applicationRequest);

        return "details";
    }

    @PostMapping(value = "/change-request")
    public String changeRequest(ApplicationRequest request){
        applicationRequestRepository.save(request);
        return "redirect:/";
    }

    @PostMapping(value = "/delete-request")
    public String deleteRequest(Long id){
        applicationRequestRepository.deleteById(id);

        return "redirect:/";
    }

}
