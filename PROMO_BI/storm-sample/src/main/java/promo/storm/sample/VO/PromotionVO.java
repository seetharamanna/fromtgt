package promo.storm.sample.VO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by A567812 on 6/15/2015.
 */
public class PromotionVO implements Serializable {
    private List<String> alternateId;
    private List<String> alternateIdSourceSystem;
    private Long page;
    private String promotionAlternateDescription;
    private String mediaTypeCode;
    private String productRepresentationCode;
    private String productRepresentationPage;
    private String promotionComment;
    private String campaignThemeCode;
    private String contactName;
    private Boolean isBrandImagePromotion;
    private Boolean isProductSalvaged;


    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }


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

    public String getPromotionAlternateDescription() {
        return promotionAlternateDescription;
    }

    public void setPromotionAlternateDescription(String promotionAlternateDescription) {
        this.promotionAlternateDescription = promotionAlternateDescription;
    }

    public String getMediaTypeCode() {
        return mediaTypeCode;
    }

    public void setMediaTypeCode(String mediaTypeCode) {
        this.mediaTypeCode = mediaTypeCode;
    }

    public String getProductRepresentationCode() {
        return productRepresentationCode;
    }

    public void setProductRepresentationCode(String productRepresentationCode) {
        this.productRepresentationCode = productRepresentationCode;
    }

    public String getProductRepresentationPage() {
        return productRepresentationPage;
    }

    public void setProductRepresentationPage(String productRepresentationPage) {
        this.productRepresentationPage = productRepresentationPage;
    }

    public String getPromotionComment() {
        return promotionComment;
    }

    public void setPromotionComment(String promotionComment) {
        this.promotionComment = promotionComment;
    }

    public String getCampaignThemeCode() {
        return campaignThemeCode;
    }

    public void setCampaignThemeCode(String campaignThemeCode) {
        this.campaignThemeCode = campaignThemeCode;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Boolean getIsBrandImagePromotion() {
        return isBrandImagePromotion;
    }

    public void setIsBrandImagePromotion(Boolean isBrandImagePromotion) {
        this.isBrandImagePromotion = isBrandImagePromotion;
    }

    public Boolean getIsProductSalvaged() {
        return isProductSalvaged;
    }

    public void setIsProductSalvaged(Boolean isProductSalvaged) {
        this.isProductSalvaged = isProductSalvaged;
    }

    public Boolean getIsPluAutoMarkdown() {
        return isPluAutoMarkdown;
    }

    public void setIsPluAutoMarkdown(Boolean isPluAutoMarkdown) {
        this.isPluAutoMarkdown = isPluAutoMarkdown;
    }

    private Boolean isPluAutoMarkdown;

}
