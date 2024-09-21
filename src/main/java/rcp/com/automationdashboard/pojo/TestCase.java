package rcp.com.automationdashboard.pojo;

import org.springframework.stereotype.Component;

@Component("rcp.com.automationdashboard.pojo")
public class TestCase {
    String id;
    int projectId;
    int totalNumberOfTestCases;
    int automatedTestCases;
    double automationPercentage;

    @Override
    public String toString() {
        return "TestCase{" +
                "id='" + id + '\'' +
                ", projectId=" + projectId +
                ", totalNumberOfTestCases=" + totalNumberOfTestCases +
                ", automatedTestCases=" + automatedTestCases +
                ", automationPercentage=" + automationPercentage +
                '}';
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public TestCase(){
        
    }

    public TestCase(String id, int projectId, int totalNumberOfTestCases, int automatedTestCases, double automationPercentage) {
        this.id = id;
        this.projectId = projectId;
        this.totalNumberOfTestCases = totalNumberOfTestCases;
        this.automatedTestCases = automatedTestCases;
        this.automationPercentage = automationPercentage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalNumberOfTestCases() {
        return totalNumberOfTestCases;
    }

    public void setTotalNumberOfTestCases(int totalNumberOfTestCases) {
        this.totalNumberOfTestCases = totalNumberOfTestCases;
    }

    public int getAutomatedTestCases() {
        return automatedTestCases;
    }

    public void setAutomatedTestCases(int automatedTestCases) {
        this.automatedTestCases = automatedTestCases;
    }

    public double getAutomationPercentage() {
        return automationPercentage;
    }

    public void setAutomationPercentage(double automationPercentage) {
        this.automationPercentage = automationPercentage;
    }


}
