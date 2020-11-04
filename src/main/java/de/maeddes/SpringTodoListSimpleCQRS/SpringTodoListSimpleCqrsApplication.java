package de.maeddes.SpringTodoListSimpleCQRS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class SpringTodoListSimpleCqrsApplication {

	@Value("${CF_INSTANCE_GUID:not_set}")
	String cfInstance;

	@Value("${HOSTNAME:not_set}")
	String hostname;

	@Value("${spring.profiles.active: none}")
	String profile;

	Logger logger = LoggerFactory.getLogger(SpringTodoListSimpleCqrsApplication.class);

	private String getInstanceId() {

		if (!hostname.equals("not_set"))
			return hostname;
		if (!cfInstance.equals("not_set"))
			return cfInstance;
		return "probably not set";

	}

	@GetMapping("/hello")
	String hello() {

		logger.info(" Call to hello method on instance: " + getInstanceId());
		return getInstanceId() + " Hello, cf-for-k8s ! (v2) ";

	}

	@GetMapping("/fail")
	String fail() {

		logger.info("Call to failing method on instance: " + getInstanceId());
		//System.exit(1);
		return "fixed!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringTodoListSimpleCqrsApplication.class, args);
	}
}




