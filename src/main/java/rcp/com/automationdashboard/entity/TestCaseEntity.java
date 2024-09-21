package rcp.com.automationdashboard.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;


@Component("rcp.com.automationdashboard.entity.TestCaseEntity")
@Entity
@Table(name="Project_TestCases")
public class TestCaseEntity {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name ="projectId", nullable = false, unique = true)
    Integer projectId;

    @Column(name = "suiteId",nullable = false)
    Integer suiteId;

    @Column(name = "totalNumberOfTestCases")
    Integer totalNumberOfTestCases;

    @Column(name = "automatedTestCases")
    Integer automatedTestCases;

    @Column(name = "automationPercentage")
    Double automationPercentage;

    public TestCaseEntity() {

    }

    public Integer getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(Integer suiteId) {
        this.suiteId = suiteId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTotalNumberOfTestCases() {
        return totalNumberOfTestCases;
    }

    public void setTotalNumberOfTestCases(Integer totalNumberOfTestCases) {
        this.totalNumberOfTestCases = totalNumberOfTestCases;
    }

    public Integer getAutomatedTestCases() {
        return automatedTestCases;
    }

    public void setAutomatedTestCases(Integer automatedTestCases) {
        this.automatedTestCases = automatedTestCases;
    }

    public Double getAutomationPercentage() {
        return automationPercentage;
    }

    public void setAutomationPercentage(Double automationPercentage) {
        this.automationPercentage = automationPercentage;
    }
}
