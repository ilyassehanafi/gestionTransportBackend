package com.example.demo;

import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private final ZoneRepository zoneRepository;
	@Autowired
	public DemoApplication(ZoneRepository zoneRepository){
		this.zoneRepository= zoneRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {

		if(zoneRepository.findAll().isEmpty()){
			ArrayList<String> geom = new ArrayList<String>();
			geom.add("GEOM(5,5)");
			zoneRepository.save(new Zone("Zone",5,2,geom));
		}

		for(Zone zone: zoneRepository.findAll()){
			System.out.println(zone);
		}
	}
}
