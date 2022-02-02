package com.example.demo.Controller;

import com.example.demo.model.AuthenticationResponse;
import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ZoneController {


    private final ZoneService zoneService;

    @Autowired
    public ZoneController( ZoneService zoneService){
        this.zoneService = zoneService;
    }

    @GetMapping("/getZone")
    public List<Zone> getZone(){
        return zoneService.getZone();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveZone")
    public ResponseEntity<AuthenticationResponse> saveZone(@RequestBody  Zone zone){
        try {
            zoneService.saveZone(zone);
        }
        catch (Exception exception){
            return new ResponseEntity("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(new AuthenticationResponse("Succesfull saved"));

    }
}
