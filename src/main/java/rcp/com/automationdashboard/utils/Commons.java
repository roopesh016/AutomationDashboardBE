package rcp.com.automationdashboard.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rcp.com.automationdashboard.controller.AutomationCountController;

import java.util.HashMap;

@Component
public class Commons {

    @Autowired
    AutomationCountController automationCountController;

    @PostConstruct
    public void init() {
            HashMap<Integer,Integer> projectSuiteMap = new HashMap<>();
            projectSuiteMap.put(10,81);
            projectSuiteMap.put(6,73);
            automationCountController.updateUsingMap(projectSuiteMap);
        }
    }
