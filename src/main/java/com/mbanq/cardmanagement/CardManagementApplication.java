package com.mbanq.cardmanagement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class CardManagementApplication implements CommandLineRunner {



	@Autowired
	ConsumerRepository consumerRepository;


	public static void main(String[] args) {
		SpringApplication.run(CardManagementApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

		Consumer consumer = new Consumer();
		consumer.setFirstname("first name");
		consumer.setLastname("last name");
		consumer.setEmail("email@gmail.com");
		consumer.setPassword("password");
		consumer.setUsername("username");
		consumer.setEnabled(true);
		consumer.setLastPasswordResetDate( new Date());
		//consumer.setAuthorities();
		consumerRepository.save(consumer);


	}

}
