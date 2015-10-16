package promo.storm.sample.VO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by A567812 on 6/17/2015.
 */
public class RewardCriteraVO implements Serializable {
    private String rewardTypeCode;
    private String rewardValue;
    private String criteriaId;


    private List<String> rewardCriteriaDpci;
    private List<Long> rewardCriteriaDepartmentId;
    private List<String> rewardCriteriaClassId;
    private List<String> rewardCriteriaItemId;

    public void setCriteriaId(String criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getCriteriaId() {
        return criteriaId;
    }

    public String getRewardTypeCode() {
        return rewardTypeCode;
    }

    public void setRewardTypeCode(String rewardTypeCode) {
        this.rewardTypeCode = rewardTypeCode;
    }

    public String getRewardValue() {
        return rewardValue;
    }

    public void setRewardValue(String rewardValue) {
        this.rewardValue = rewardValue;
    }


    public List<String> getRewardCriteriaDpci() {
        return rewardCriteriaDpci;
    }

    public void setRewardCriteriaDpci(List<String> rewardCriteriaDpci) {
        this.rewardCriteriaDpci = rewardCriteriaDpci;
    }

    public List<Long> getRewardCriteriaDepartmentId() {
        return rewardCriteriaDepartmentId;
    }

    public void setRewardCriteriaDepartmentId(List<Long> rewardCriteriaDepartmentId) {
        this.rewardCriteriaDepartmentId = rewardCriteriaDepartmentId;
    }

    public List<String> getRewardCriteriaClassId() {
        return rewardCriteriaClassId;
    }

    public void setRewardCriteriaClassId(List<String> rewardCriteriaClassId) {
        this.rewardCriteriaClassId = rewardCriteriaClassId;
    }

    public List<String> getRewardCriteriaItemId() {
        return rewardCriteriaItemId;
    }

    public void setRewardCriteriaItemId(List<String> rewardCriteriaItemId) {
        this.rewardCriteriaItemId = rewardCriteriaItemId;
    }
}
