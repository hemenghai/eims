package edu.ncu.eims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 核心启动类
 *
 * @author hemenghai
 * @date 2019-02-23
 */
@EnableSwagger2
@SpringBootApplication
public class EimsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EimsApplication.class, args);
	}

}
