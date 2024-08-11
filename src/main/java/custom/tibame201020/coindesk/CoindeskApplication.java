package custom.tibame201020.coindesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CoindeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoindeskApplication.class, args);
	}

}
