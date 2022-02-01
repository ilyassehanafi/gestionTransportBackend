package com.example.demo.service;

import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneService implements ZoneServiceInterface{

    private final ZoneRepository zoneRepository;

    @Autowired
    public ZoneService(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public String saveZone(Zone zone) {
        zoneRepository.save(new Zone(zone.getZoneName(),zone.getNodeNumber(),zone.getLinkNumber(),zone.getGeometry()));
        return "Added";
    }

    @Override
    public List<Zone> getZone() {
        return zoneRepository.findAll();
    }
}
