package com.example.demo.Controller;

import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ZoneController {


    private final ZoneRepository zoneRepository;

    @Autowired
    public ZoneController(ZoneRepository zoneRepository){
        this.zoneRepository = zoneRepository;
    }
    @GetMapping("/home")
    public List<Zone> getZone(){
        return zoneRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/test")
    public String saveZone(@RequestBody  Zone zone){
        zoneRepository.save(new Zone(zone.getZoneName(),zone.getNodeNumber(),zone.getLinkNumber(),zone.getGeometry()));
        return "Added";
    }
}
