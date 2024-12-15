package zev.plagiarismdetectorserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.SpringVersion;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableFeignClients
public class PlagiarismDetectorServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(PlagiarismDetectorServerApplication.class, args);
  }

}
