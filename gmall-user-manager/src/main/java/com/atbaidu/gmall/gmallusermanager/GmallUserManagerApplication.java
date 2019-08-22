package com.atbaidu.gmall.gmallusermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.atbaidu.gmall.gmallusermanager.mapper")
@SpringBootApplication
public class GmallUserManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmallUserManagerApplication.class, args);
	}

}
