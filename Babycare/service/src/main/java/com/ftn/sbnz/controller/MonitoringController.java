package com.ftn.sbnz.controller;

import com.ftn.sbnz.repository.MonitoringDataLoader;
import com.ftn.sbnz.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitoring")
@EnableAsync
public class MonitoringController {

    @Autowired
    MonitoringService monitoringService;
    @PostMapping("/load")
    public String loadData() {
        monitoringService.loadDataAsync("data.csv");
        return "Loading...";
    }


}
