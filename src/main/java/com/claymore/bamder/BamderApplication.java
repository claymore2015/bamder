package com.claymore.bamder;

import com.claymore.bamder.common.config.FileStorageProperties;
import com.claymore.bamder.common.config.StubLoggingFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class BamderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BamderApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Filter logFilter() {
		return new StubLoggingFilter();
	}
}
