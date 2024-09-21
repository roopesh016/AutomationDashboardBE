package rcp.com.automationdashboard.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rcp.com.automationdashboard.entity.TestCaseEntity;
import rcp.com.automationdashboard.pojo.TestCase;
import rcp.com.automationdashboard.repository.TestCaseRepo;
import rcp.com.automationdashboard.service.AutomationCountService;

import java.util.*;

@Component
@RestController
public class AutomationCountController {

    HashMap<String,Object> Hmap = new HashMap<>();

    @Autowired
    AutomationCountService automationCountService;

    @Autowired
    TestCaseRepo testCaseRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TestCase testCase;

    @Autowired
    TestCaseEntity testCaseEntity;

    @CrossOrigin
    @GetMapping("/fetchTotalNumberOfTestCases/{projectId}/{suiteId}")
    public List<TestCaseEntity> getAutomationCount(@PathVariable final int projectId, @PathVariable final int suiteId){
        //Fetch the latest records from the DB based upon the unique key as projectid & suite Id.
        return  automationCountService.fetchByProjectIdAndSuiteId(projectId,suiteId);
    }

//    @CrossOrigin
//    @GetMapping("/fetchProject/{id}")
//    public Optional<TestCaseEntity> fetchProjectById(@PathVariable int id){
//       return automationCountService.getProjectById(id);
//    }

    @CrossOrigin
    @GetMapping("/fetchAllProjects")
    public List<TestCaseEntity> fetchAll(){
        return automationCountService.getListOfProjects();
    }





//     @GetMapping("/fetchTotalNumberOfTestCases/{projectId}/{suiteId}")
//     public TestCase getTotalNumberOfTestCases(@PathVariable final int projectId, @PathVariable final int suiteId){
//
//         HashMap<String,Object> Hmap = new HashMap<>();
//         Hmap = automationCountService.getResponseBody(String.valueOf(projectId),String.valueOf(suiteId));
//         for(Map.Entry entry:Hmap.entrySet()){
//             if(entry.getKey().equals("Total test cases count")){
//                testCase.setTotalNumberOfTestCases(Integer.parseInt(String.valueOf(entry.getValue())));
//             } else if(entry.getKey().equals("Total Automated test cases count")){
//                 testCase.setAutomatedTestCases(Integer.parseInt(String.valueOf(entry.getValue())));
//             } else if(entry.getKey().equals("automationPercentage")){
//                 testCase.setAutomationPercentage(Double.parseDouble(String.valueOf(entry.getValue())));
//             }
//         }
//         return new TestCase(UUID.randomUUID().toString(),projectId,testCase.getTotalNumberOfTestCases(),testCase.getAutomatedTestCases(),testCase.getAutomationPercentage());
//     }

//     public void updateTestCasesCount(@PathVariable final int projectId, @PathVariable final int suiteId){
//         //Save the records in the DB for every 15 mins
//         Hmap  = automationCountService.getResponseBody(String.valueOf(projectId),String.valueOf(suiteId));
//         for(Map.Entry entry:Hmap.entrySet()){
//             testCaseEntity.setProjectId(projectId);
//             testCaseEntity.setSuiteId(suiteId);
//             if(entry.getKey().equals("Total test cases count")){
//                 testCaseEntity.setTotalNumberOfTestCases(Integer.parseInt(String.valueOf(entry.getValue())));
//             } else if(entry.getKey().equals("Total Automated test cases count")){
//                 testCaseEntity.setAutomatedTestCases(Integer.parseInt(String.valueOf(entry.getValue())));
//             } else if(entry.getKey().equals("automationPercentage")){
//                 testCaseEntity.setAutomationPercentage(Double.parseDouble(String.valueOf(entry.getValue())));
//             }
//         }
//         testCaseRepo.save(testCaseEntity);
//     }


    public void updateTestCasesCountList(@PathVariable final List<Integer> projectId, @PathVariable final List<Integer> suiteId){
        //Save the records in the DB for every 15 mins
        Set<TestCaseEntity> testCaseEntity = new HashSet<>();
        for(int i=0; i<=projectId.size()-1 && i<=suiteId.size()-1; i++){
            TestCaseEntity projectDetails = testCaseRepo.findByProjectId(projectId.get(i));
            if(projectDetails.getProjectId().equals(projectId.get(i))) {
                Hmap  = automationCountService.getResponseBody(String.valueOf(projectId.get(i)),String.valueOf(suiteId.get(i)));
                for(Map.Entry entry:Hmap.entrySet()){

                    if(entry.getKey().equals("Total test cases count")){
                        jdbcTemplate.execute("UPDATE project_test_cases set total_number_of_test_cases = "+Integer.parseInt(String.valueOf(entry.getValue()))+"" +
                                " where project_id = "+String.valueOf(projectId.get(i))+" and suite_id ="+String.valueOf(suiteId.get(i))+";");

                    } else if(entry.getKey().equals("Total Automated test cases count")){
                        jdbcTemplate.execute("UPDATE project_test_cases set automated_test_cases = "+Integer.parseInt(String.valueOf(entry.getValue()))+"" +
                                " where project_id = "+String.valueOf(projectId.get(i))+" and suite_id ="+String.valueOf(suiteId.get(i))+";");

                    } else if(entry.getKey().equals("automationPercentage")){
                        jdbcTemplate.execute("UPDATE project_test_cases set automation_percentage = "+Double.parseDouble(String.valueOf(entry.getValue()))+"" +
                                " where project_id = "+projectId.get(i)+" and suite_id ="+suiteId.get(i)+";");
                    }
                }

            }
        }
        testCaseRepo.saveAll(testCaseEntity);
    }



    public void updateUsingMap(HashMap<Integer, Integer> projectSuiteMap) {
        try {
            for (Map.Entry<Integer, Integer> entry : projectSuiteMap.entrySet()) {
                int projectId = entry.getKey();
                int suiteId = entry.getValue();
                TestCaseEntity testCaseEntity = new TestCaseEntity();
                testCaseEntity.setProjectId(projectId);
                testCaseEntity.setSuiteId(suiteId);
                testCaseRepo.save(testCaseEntity);
            }
        } catch (DataIntegrityViolationException ex) {
            System.out.println("Project ID record already exists!");
        }
    }


    //npm run start
}
