package com.atbaidu.gmall.gmallmanageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;
@EnableTransactionManagement
@MapperScan("com.atbaidu.gmall.gmallmanageservice.mapper")
@SpringBootApplication
public class GmallManageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmallManageServiceApplication.class, args);
	}

}
