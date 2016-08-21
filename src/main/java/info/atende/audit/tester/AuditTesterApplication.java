package info.atende.audit.tester;

import info.atende.audition.model.AuditEvent;
import info.atende.audition.model.Resource;
import info.atende.audition.model.SecurityLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class AuditTesterApplication implements CommandLineRunner {
	private static Logger logger = LoggerFactory.getLogger(AuditTesterApplication.class);
	@Autowired
	private RabbitTemplate template;
	@Autowired
	private ApplicationContext applicationContext;

	@Value("${spring.rabbitmq.host}")
	private String rabbitHost;

	public static void main(String[] args) {
		SpringApplication.run(AuditTesterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("Runnning with --spring.rabbitmq.host=" +rabbitHost);

		java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
		Resource resource = new Resource("application");
		AuditEvent event = new AuditEvent("audit-tester",System.getProperty("user.name"),"application_run", resource,
				LocalDateTime.now(),localMachine.getHostAddress(), SecurityLevel.NORMAL);

		logger.info("Sending event: " + event);
		template.convertAndSend(event);
		logger.info("Sended");
		logger.info("Shutdown");
		((ConfigurableApplicationContext)applicationContext).close();

	}
}
