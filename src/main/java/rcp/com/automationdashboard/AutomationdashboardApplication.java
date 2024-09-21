package rcp.com.automationdashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import rcp.com.automationdashboard.controller.SchedulerController;
import rcp.com.automationdashboard.service.AutomationCountService;

@SpringBootApplication
@EnableScheduling
public class AutomationdashboardApplication {


	public static void main(String[] args) {
		SpringApplication.run(AutomationdashboardApplication.class, args);
	}

}
