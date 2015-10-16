package promo.storm.sample.VO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by A567812 on 6/17/2015.
 */
public class QualifyCriteriaVO implements Serializable{
    private String criteriaId;
    private List<String> dpci;
    private List<Long> departmentId;
    private List<String> classId;
    private List<String> itemId;
    private String offerRetailAmount;
    private String specialCostAmount;
    private Long originalBohQuantity;
    private Long revisedBohQuantity;
    private Long originalPicturedBohQuantity;


    private Long originalPicturedProductQuantity;
    private Long revisedPicturedProductQuantity;
    private Long originalUnitQuantity;
    private Long revisedUnitQuantity;
    private String productPriceProgramCode;

    private String highOfferRetailAmount;
    private String lowOfferRetailAmount;
    private String weightedOfferRetailAmount;
    private String weightedRegularRetailAmount;



    public String getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(String criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getOfferRetailAmount() {
        return offerRetailAmount;
    }

    public void setOfferRetailAmount(String offerRetailAmount) {
        this.offerRetailAmount = offerRetailAmount;
    }

    public String getSpecialCostAmount() {
        return specialCostAmount;
    }

    public void setSpecialCostAmount(String specialCostAmount) {
        this.specialCostAmount = specialCostAmount;
    }

    public Long getOriginalBohQuantity() {
        return originalBohQuantity;
    }

    public void setOriginalBohQuantity(Long originalBohQuantity) {
        this.originalBohQuantity = originalBohQuantity;
    }

    public Long getRevisedBohQuantity() {
        return revisedBohQuantity;
    }

    public void setRevisedBohQuantity(Long revisedBohQuantity) {
        this.revisedBohQuantity = revisedBohQuantity;
    }

    public Long getOriginalPicturedBohQuantity() {
        return originalPicturedBohQuantity;
    }

    public void setOriginalPicturedBohQuantity(Long originalPicturedBohQuantity) {
        this.originalPicturedBohQuantity = originalPicturedBohQuantity;
    }

    public List<String> getDpci() {
        return dpci;
    }

    public void setDpci(List<String> dpci) {
        this.dpci = dpci;
    }

    public List<Long> getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(List<Long> departmentId) {
        this.departmentId = departmentId;
    }

    public List<String> getClassId() {
        return classId;
    }

    public void setClassId(List<String> classId) {
        this.classId = classId;
    }

    public List<String> getItemId() {
        return itemId;
    }

    public void setItemId(List<String> itemId) {
        this.itemId = itemId;
    }



    public Long getOriginalPicturedProductQuantity() {
        return originalPicturedProductQuantity;
    }

    public void setOriginalPicturedProductQuantity(Long originalPicturedProductQuantity) {
        this.originalPicturedProductQuantity = originalPicturedProductQuantity;
    }

    public Long getRevisedPicturedProductQuantity() {
        return revisedPicturedProductQuantity;
    }

    public void setRevisedPicturedProductQuantity(Long revisedPicturedProductQuantity) {
        this.revisedPicturedProductQuantity = revisedPicturedProductQuantity;
    }

    public Long getOriginalUnitQuantity() {
        return originalUnitQuantity;
    }

    public void setOriginalUnitQuantity(Long originalUnitQuantity) {
        this.originalUnitQuantity = originalUnitQuantity;
    }

    public Long getRevisedUnitQuantity() {
        return revisedUnitQuantity;
    }

    public void setRevisedUnitQuantity(Long revisedUnitQuantity) {
        this.revisedUnitQuantity = revisedUnitQuantity;
    }

    public String getProductPriceProgramCode() {
        return productPriceProgramCode;
    }

    public void setProductPriceProgramCode(String productPriceProgramCode) {
        this.productPriceProgramCode = productPriceProgramCode;
    }

    public String getHighOfferRetailAmount() {
        return highOfferRetailAmount;
    }

    public void setHighOfferRetailAmount(String highOfferRetailAmount) {
        this.highOfferRetailAmount = highOfferRetailAmount;
    }

    public String getLowOfferRetailAmount() {
        return lowOfferRetailAmount;
    }

    public void setLowOfferRetailAmount(String lowOfferRetailAmount) {
        this.lowOfferRetailAmount = lowOfferRetailAmount;
    }

    public String getWeightedOfferRetailAmount() {
        return weightedOfferRetailAmount;
    }

    public void setWeightedOfferRetailAmount(String weightedOfferRetailAmount) {
        this.weightedOfferRetailAmount = weightedOfferRetailAmount;
    }

    public String getWeightedRegularRetailAmount() {
        return weightedRegularRetailAmount;
    }

    public void setWeightedRegularRetailAmount(String weightedRegularRetailAmount) {
        this.weightedRegularRetailAmount = weightedRegularRetailAmount;
    }


}
