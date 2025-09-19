package com.ftn.sbnz;

import com.ftn.sbnz.service.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.ftn.sbnz.model")
@EnableJpaRepositories(basePackages = "com.ftn.sbnz")
public class ServiceApplication  implements CommandLineRunner {
	
	public static void main(String[] args) {
				SpringApplication.run(ServiceApplication.class, args);
	}

	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.sbnz", "kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(1000);
		KieContainer kieContainer = ks.getKieClasspathContainer();
		return kieContainer;
	}

	@Override
	public void run(String... args) throws Exception{
		Test.main();
	}
}
