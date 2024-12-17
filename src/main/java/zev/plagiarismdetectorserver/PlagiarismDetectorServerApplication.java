package zev.plagiarismdetectorserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class PlagiarismDetectorServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(PlagiarismDetectorServerApplication.class, args);
  }

}
