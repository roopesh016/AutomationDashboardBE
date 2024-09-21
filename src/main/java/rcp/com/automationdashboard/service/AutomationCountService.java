/**
 * Author:    Roopesh
 * Created:   30.04.2023
 *
 **/


package rcp.com.automationdashboard.service;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import rcp.com.automationdashboard.controller.AutomationCountController;
import rcp.com.automationdashboard.entity.TestCaseEntity;
import rcp.com.automationdashboard.repository.TestCaseRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class AutomationCountService {


    @Autowired
    TestCaseRepo testCaseRepo;


    /**
     * The following method helps us to fetch the total test cases count from test rail suite
     * @param projectId is of string format
     * @param suiteId is of string format
     * @return
     */


    public HashMap<String,Object> getResponseBody(String projectId,String suiteId){

        HashMap<String,Object> hMap = new HashMap();
        try {
        RequestSpecification requestSpecification = RestAssured.given().auth().preemptive().basic("roopesh.t@rakuten.com","16041995");

        Response response  = requestSpecification.contentType("application/json").accept("application/json").when().log().all().get(
                "https://rcp.testrail.net/index.php?/api/v2/get_cases/"+projectId+"&suite_id="+suiteId)
                .then().extract().response();

        JSONParser j = new JSONParser();

        JSONObject jObj = (JSONObject) j.parse(response.getBody().asString());

        List<JSONObject> jsonObject  = JsonPath.parse(jObj).read("$.cases");
            int manualCasesCount = 0;
            int totalAutomatedTestCasesCount = 0;

       for(int i=0; i<=jsonObject.size()-1; i++){
           if(JsonPath.parse(jsonObject.get(i)).read("$.type_id").toString().equalsIgnoreCase("7")){
                  manualCasesCount++;
           } else if(JsonPath.parse(jsonObject.get(i)).read("$.type_id").toString().equalsIgnoreCase("3")){
               totalAutomatedTestCasesCount++;
           }
         }
       int totalTestCasesCount = manualCasesCount+totalAutomatedTestCasesCount;
       double automationPercentage =  calculatePercentage(totalAutomatedTestCasesCount,totalTestCasesCount);
       hMap.put("Total test cases count",totalTestCasesCount);
       hMap.put("Total Automated test cases count",totalAutomatedTestCasesCount);
       hMap.put("automationPercentage",automationPercentage);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hMap;
    }

    /**
     * The following method helps us to calculate the percentage
      * @param obtained is of int type
     * @param total is of int type
     * @return
     */

    public  double calculatePercentage(int obtained, int total) {
        return obtained * 100 / total;
    }

    public Optional<TestCaseEntity> getProjectById(int id){
        return Optional.ofNullable(testCaseRepo.findById(id).orElse(null));
    }


    public List<TestCaseEntity> getListOfProjects(){
        return testCaseRepo.findAll();
    }


    public List<TestCaseEntity> fetchByProjectIdAndSuiteId(int projectId,int suiteId){
        TestCaseEntity example = new TestCaseEntity();
        example.setProjectId(projectId);
        example.setSuiteId(suiteId);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("projectId", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("suiteId", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<TestCaseEntity> queryExample = Example.of(example, matcher);
        return testCaseRepo.findAll(queryExample);
    }

}
