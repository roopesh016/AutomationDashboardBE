package rcp.com.automationdashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class SchedulerController {

    @Autowired
    AutomationCountController automationCountController;

    @Scheduled(fixedRate = 200000)
    public void fixedRateSch(){
        List<Integer> projectId = new ArrayList<>();
        projectId.add(10);
        projectId.add(6);
        List<Integer> suiteId = new ArrayList<>();
        suiteId.add(81);
        suiteId.add(73);
        automationCountController.updateTestCasesCountList(projectId,suiteId);
    }


//    public void updateProjectMappingRecords(){
//        HashMap<Integer,Integer> projectSuiteMap = new HashMap<>();
//        projectSuiteMap.put(10,81);
//        projectSuiteMap.put(6,73);
//        automationCountController.updateUsingMap(projectSuiteMap);
//    }

}
