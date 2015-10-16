package promo.storm.sample.VO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by A567812 on 6/12/2015.
 */
public class OfferTypeJsonVO implements Serializable {

    private String offerId;
    private List<String> aleternateId;
    private List<String> srcSystemCd;
    private String startDateTime;
    private String endDateTime;
    private boolean isRaincheckApplicable;
    private boolean hasGiftWithPurchase;
    private String finDetSetUpLvlCode;
    private String offerDetailDescription;
    private Boolean isCompleteForUse;
    private List<PromotionVO> promotionVO;
    private List<OfferDetailVO> offerDetailVO;

    public List<OfferDetailVO> getOfferDetailVO() {
        return offerDetailVO;
    }

    public void setOfferDetailVO(List<OfferDetailVO> offerDetailVO) {
        this.offerDetailVO = offerDetailVO;
    }


    public List<PromotionVO> getPromotionVO() {
        return promotionVO;
    }

    public void setPromotionVO(List<PromotionVO> promotionVO) {
        this.promotionVO = promotionVO;
    }


    public Boolean getIsCompleteForUse() {
        return isCompleteForUse;
    }

    public void setIsCompleteForUse(Boolean isCompleteForUse) {
        this.isCompleteForUse = isCompleteForUse;
    }


    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }


    public String getOfferDetailDescription() {
        return offerDetailDescription;
    }

    public void setOfferDetailDescription(String offerDetailDescription) {
        this.offerDetailDescription = offerDetailDescription;
    }


    public List<String> getAleternateId() {
        return aleternateId;
    }

    public void setAleternateId(List<String> aleternateId) {
        this.aleternateId = aleternateId;
    }

    public List<String> getSrcSystemCd() {
        return srcSystemCd;
    }

    public void setSrcSystemCd(List<String> srcSystemCd) {
        this.srcSystemCd = srcSystemCd;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public boolean isRaincheckApplicable() {
        return isRaincheckApplicable;
    }

    public void setRaincheckApplicable(boolean isRaincheckApplicable) {
        this.isRaincheckApplicable = isRaincheckApplicable;
    }

    public boolean isHasGiftWithPurchase() {
        return hasGiftWithPurchase;
    }

    public void setHasGiftWithPurchase(boolean hasGiftWithPurchase) {
        this.hasGiftWithPurchase = hasGiftWithPurchase;
    }

    public String getFinDetSetUpLvlCode() {
        return finDetSetUpLvlCode;
    }

    public void setFinDetSetUpLvlCode(String finDetSetUpLvlCode) {
        this.finDetSetUpLvlCode = finDetSetUpLvlCode;
    }


}
