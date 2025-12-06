package com.example.storeapp_consumer_eureka_ribbon;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StoreappConsumerEurekaRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreappConsumerEurekaRibbonApplication.class, args);
	}

	//Cross cutting concern - how much time my method took?

	@Bean
	public TimedAspect timedAspect(MeterRegistry meterRegistry) {
		return new TimedAspect(meterRegistry);
	}

}
