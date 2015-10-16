package promo.storm.sample.VO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by A567812 on 6/14/2015.
 */
public class OfferDetailVO implements Serializable {
    private String offerDetailId;
    private List<String> alternateId;
    private List<String> alternateIdSourceSystem;
    private String offerDetailDescription;
    private Long locationObjectQuantity;
    private List<QualifyCriteriaVO> qualifyCriteriaVO;

    private List<RewardCriteraVO> rewardCriteraVO;


    public List<String> getAlternateId() {
        return alternateId;
    }

    public void setAlternateId(List<String> alternateId) {
        this.alternateId = alternateId;
    }

    public List<String> getAlternateIdSourceSystem() {
        return alternateIdSourceSystem;
    }

    public void setAlternateIdSourceSystem(List<String> alternateIdSourceSystem) {
        this.alternateIdSourceSystem = alternateIdSourceSystem;
    }


    public List<QualifyCriteriaVO> getQualifyCriteriaVO() {
        return qualifyCriteriaVO;
    }

    public void setQualifyCriteriaVO(List<QualifyCriteriaVO> qualifyCriteriaVO) {
        this.qualifyCriteriaVO = qualifyCriteriaVO;
    }

    public List<RewardCriteraVO> getRewardCriteraVO() {

        return rewardCriteraVO;
    }

    public void setRewardCriteraVO(List<RewardCriteraVO> rewardCriteraVO) {
        this.rewardCriteraVO = rewardCriteraVO;
    }

    public String getOfferDetailId() {
        return offerDetailId;
    }

    public void setOfferDetailId(String offerDetailId) {
        this.offerDetailId = offerDetailId;
    }

    public String getOfferDetailDescription() {
        return offerDetailDescription;
    }

    public void setOfferDetailDescription(String offerDetailDescription) {
        this.offerDetailDescription = offerDetailDescription;
    }

    public Long getLocationObjectQuantity() {
        return locationObjectQuantity;
    }

    public void setLocationObjectQuantity(Long locationObjectQuantity) {
        this.locationObjectQuantity = locationObjectQuantity;
    }

}

