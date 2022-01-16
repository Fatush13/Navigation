package ee.task.nagivation;

import ee.task.nagivation.service.BaseStationInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    @Autowired
    BaseStationInitializer baseStationInitializer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    void init() {
        baseStationInitializer.initializeBaseStations();
    }

}
