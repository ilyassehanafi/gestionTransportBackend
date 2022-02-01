package com.example.demo.Controller;

import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ZoneController {


    private final ZoneService zoneService;

    @Autowired
    public ZoneController( ZoneService zoneService){
        this.zoneService = zoneService;
    }
    @GetMapping("/home")
    public List<Zone> getZone(){
        return zoneService.getZone();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveZone")
    public String saveZone(@RequestBody  Zone zone){
        return zoneService.saveZone(zone) ;
    }
}
