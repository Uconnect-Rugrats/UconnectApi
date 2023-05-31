package uco.doo.rugrats.uconnect.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"uco.doo.rugrats.uconnect"})
public class UconnectApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UconnectApiApplication.class, args);
	}

}
