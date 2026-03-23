package finki.labfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LabFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabFinalApplication.class, args);
    }

}
