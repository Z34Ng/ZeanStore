package com.ecommerce.zeanstore.ClothesStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class ClothesStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClothesStoreApplication.class, args);
	}

}
