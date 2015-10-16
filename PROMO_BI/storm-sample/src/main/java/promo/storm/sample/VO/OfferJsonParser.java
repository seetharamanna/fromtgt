package promo.storm.sample.VO;

import org.apache.avro.generic.GenericData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by A567812 on 6/12/2015.
 */
public class OfferJsonParser {
    public static OfferTypeJsonVO parseJSON(JSONObject jsonObject1) {
/*
        final String filePath = "offer_json_new.txt";

        // read the json file
        try {
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonObjectArray = (JSONArray) jsonParser.parse(reader);
            List<OfferTypeJsonVO> offerTypeJsonVOs = new ArrayList<OfferTypeJsonVO>();
*/


//            for (Object aJsonObject : jsonObjectArray) {
//                JSONObject jsonObject = (JSONObject) aJsonObject;
        JSONObject jsonObject=(JSONObject)jsonObject1.get("offerProduct");
        OfferTypeJsonVO offerTypeJsonVO = parseOfferBasicInfo(jsonObject);
        parseOfferDetailInfo(jsonObject, offerTypeJsonVO);
        parsePromotionDetails(jsonObject, offerTypeJsonVO);
        return offerTypeJsonVO;
//                offerTypeJsonVOs.add(offerTypeJsonVO);
//            }

    }

//            StringBuilder offerHeadeStr = new StringBuilder();
//            for(OfferTypeJsonVO offerTypeJsonVO:offerTypeJsonVOs) {
//
//                    offerHeadeStr.append(offerTypeJsonVO.getOfferId() + "," + offerTypeJsonVO.getAleternateId().get(0) + "," + offerTypeJsonVO.getSrcSystemCd().get(0) + "," + offerTypeJsonVO.getAleternateId().get(1) + "," + offerTypeJsonVO.getSrcSystemCd().get(1) + "," + offerTypeJsonVO.getOfferDetailDescription() + "," + offerTypeJsonVO.getStartDateTime() + "," + offerTypeJsonVO.getEndDateTime() + "," + offerTypeJsonVO.isRaincheckApplicable() + "," + offerTypeJsonVO.isHasGiftWithPurchase() + "," + offerTypeJsonVO.getFinDetSetUpLvlCode() + "," + offerTypeJsonVO.getIsCompleteForUse());
//                    offerHeadeStr.append("\n");
//
//            }
//            System.out.println("Header:\n" + offerHeadeStr.toString());
//
//            StringBuilder offerDetailStr = new StringBuilder();
//            for(OfferTypeJsonVO offerTypeJsonVO:offerTypeJsonVOs) {
//                List<OfferDetailVO> offerDetailVOs;
//                offerDetailVOs = offerTypeJsonVO.getOfferDetailVO();
//                for (OfferDetailVO offerDetailVO : offerDetailVOs) {
//                    for (int k = 0; k < offerDetailVO.getAlternateId().size(); k++) {
//                        offerDetailStr.append(offerTypeJsonVO.getOfferId()+","+offerDetailVO.getOfferDetailId() + "," + offerDetailVO.getAlternateId().get(k) + "," + offerDetailVO.getAlternateIdSourceSystem().get(k) + "," + offerDetailVO.getOfferDetailDescription() + "," + offerDetailVO.getLocationObjectQuantity());
//                        offerDetailStr.append("\n");
//                    }
//                }
//            }
//            System.out.println("OfferDetails:\n" + offerDetailStr.toString());
//
//
//            StringBuilder qualifyCriteriaStr = new StringBuilder();
//            for(OfferTypeJsonVO offerTypeJsonVO:offerTypeJsonVOs) {
//                List<OfferDetailVO> offerDetailVOs;
//                offerDetailVOs = offerTypeJsonVO.getOfferDetailVO();
//                for (OfferDetailVO offerDetailVO : offerDetailVOs) {
//                    QualifyCriteriaVO qualifyCriteriaVO;
//                    qualifyCriteriaVO = offerDetailVO.getQualifyCriteriaVO();
//                    for (int k = 0; k < qualifyCriteriaVO.getDepartmentId().size(); k++) {
//                        qualifyCriteriaStr.append(offerTypeJsonVO.getOfferId()+","+offerDetailVO.getOfferDetailId() + "," + offerDetailVO.getOfferDetailDescription() + "," + qualifyCriteriaVO.getCriteriaId() + "," + qualifyCriteriaVO.getDpci().get(k) + "," + qualifyCriteriaVO.getDepartmentId().get(k) + "," + qualifyCriteriaVO.getClassId().get(k) + "," + qualifyCriteriaVO.getItemId().get(k) + "," + qualifyCriteriaVO.getOfferRetailAmount() + "," + qualifyCriteriaVO.getSpecialCostAmount() + "," + qualifyCriteriaVO.getOriginalBohQuantity() + "," + qualifyCriteriaVO.getRevisedBohQuantity() + "," + qualifyCriteriaVO.getOriginalPicturedBohQuantity() + "," + qualifyCriteriaVO.getOriginalPicturedProductQuantity() + "," + qualifyCriteriaVO.getRevisedPicturedProductQuantity() + "," + qualifyCriteriaVO.getOriginalUnitQuantity() + "," + qualifyCriteriaVO.getRevisedUnitQuantity() + "," + qualifyCriteriaVO.getProductPriceProgramCode() + "," + qualifyCriteriaVO.getHighOfferRetailAmount() + "," + qualifyCriteriaVO.getLowOfferRetailAmount() + "," + qualifyCriteriaVO.getWeightedOfferRetailAmount() + "," + qualifyCriteriaVO.getWeightedRegularRetailAmount() + "," + offerDetailVO.getLocationObjectQuantity());
//                        qualifyCriteriaStr.append("\n");
//                    }
//                }
//            }
//            System.out.println("Qualify criteria:\n"+qualifyCriteriaStr.toString());
//
//            StringBuilder promotionStr = new StringBuilder();
//            for(OfferTypeJsonVO offerTypeJsonVO:offerTypeJsonVOs) {
//                    PromotionVO promotionVO;
//                    promotionVO = offerTypeJsonVO.getPromotionVO();
//                    promotionStr.append(offerTypeJsonVO.getOfferId()+","+promotionVO.getAlternateId().get(0) + "," + promotionVO.getAlternateIdSourceSystem().get(0) + "," + promotionVO.getAlternateId().get(1) + "," + promotionVO.getAlternateIdSourceSystem().get(1) + "," + promotionVO.getPromotionAlternateDescription() + "," + promotionVO.getMediaTypeCode() + "," + promotionVO.getProductRepresentationCode() + "," + promotionVO.getPage() + "," + promotionVO.getPromotionComment() + "," + promotionVO.getCampaignThemeCode() + "," + promotionVO.getContactName() + "," + promotionVO.getIsBrandImagePromotion() + "," + promotionVO.getIsProductSalvaged() + "," + promotionVO.getIsPluAutoMarkdown());
//                    promotionStr.append("\n");
//            }
//
//            StringBuilder rewardCriteriaStr = new StringBuilder();
//
//            for(OfferTypeJsonVO offerTypeJsonVO:offerTypeJsonVOs) {
//                List<OfferDetailVO> offerDetailVOs;
//                offerDetailVOs = offerTypeJsonVO.getOfferDetailVO();
//                for (OfferDetailVO offerDetailVO : offerDetailVOs) {
//                    RewardCriteraVO rewardCriteraVO;
//                    rewardCriteraVO = offerDetailVO.getRewardCriteraVO();
//                    for (int k = 0; k < rewardCriteraVO.getRewardCriteriaDepartmentId().size(); k++) {
//
//                        rewardCriteriaStr.append(offerTypeJsonVO.getOfferId()+","+offerDetailVO.getOfferDetailId() + "," + rewardCriteraVO.getRewardTypeCode() + "," + rewardCriteraVO.getRewardValue() + "," + rewardCriteraVO.getRewardCriteriaDpci().get(k) + "," + rewardCriteraVO.getRewardCriteriaDepartmentId().get(k) + "," + rewardCriteraVO.getRewardCriteriaClassId().get(k) + "," + rewardCriteraVO.getRewardCriteriaItemId().get(k));
//                        rewardCriteriaStr.append("\n");
//                    }
//
//                }
//            }
//            System.out.println("Reward Criteria:\n"+rewardCriteriaStr.toString());
//
//
//            System.out.println("Promotion:\n"+promotionStr.toString());
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }

    private static List<PromotionVO> parsePromotionDetails(JSONObject jsonObject, OfferTypeJsonVO offerTypeJsonVO) {
        List<PromotionVO> promotionVOs = new ArrayList<PromotionVO>();
        List<String> alternateIds = new ArrayList<String>();
        List<String> alternateIdSourceSystems = new ArrayList<String>();

        JSONArray promotion = (JSONArray) jsonObject.get("promotion");
        for (Object aPromotion : promotion) {
            JSONObject aPromotionObj = (JSONObject) aPromotion;
            PromotionVO promotionVO = new PromotionVO();
            JSONArray promotionAlternateId = (JSONArray) aPromotionObj.get("promotionAlternateId");
            for (Object aPromotionAlternateId : promotionAlternateId) {
                JSONObject aPromotionAlternateIdObj = (JSONObject) aPromotionAlternateId;
                alternateIds.add((String) aPromotionAlternateIdObj.get("alternateId"));
                JSONObject alternateIdSourceSystem = (JSONObject) aPromotionAlternateIdObj.get("alternateIdSourceSystem");
                alternateIdSourceSystems.add((String) alternateIdSourceSystem.get("id"));
            }
            promotionVO.setAlternateId(alternateIds);
            promotionVO.setAlternateIdSourceSystem(alternateIdSourceSystems);
            promotionVO.setPromotionAlternateDescription((String) aPromotionObj.get("promotionAlternateDescription"));
            JSONObject mediaType = (JSONObject) aPromotionObj.get("mediaType");
            promotionVO.setMediaTypeCode((String) mediaType.get("code"));

            JSONObject offerPlacement = (JSONObject) aPromotionObj.get("offerPlacement");
            JSONObject productRepresentation = (JSONObject) offerPlacement.get("productRepresentation");
            promotionVO.setProductRepresentationCode((String) productRepresentation.get("code"));

            promotionVO.setPage((Long) offerPlacement.get("page"));

            JSONObject legacyPromotionElements = (JSONObject) aPromotionObj.get("legacyPromotionElements");
            promotionVO.setPromotionComment((String) legacyPromotionElements.get("promotionComment"));
            JSONObject campaignTheme = (JSONObject) legacyPromotionElements.get("campaignTheme");
            promotionVO.setCampaignThemeCode((String) campaignTheme.get("code"));
            promotionVO.setContactName((String) legacyPromotionElements.get("contactName"));
            promotionVO.setIsBrandImagePromotion((Boolean) legacyPromotionElements.get("isBrandImagePromotion"));
            promotionVO.setIsProductSalvaged((Boolean) legacyPromotionElements.get("isProductSalvaged"));
            promotionVO.setIsPluAutoMarkdown((Boolean) legacyPromotionElements.get("isPluAutoMarkdown"));
            promotionVOs.add(promotionVO);
        }

        offerTypeJsonVO.setPromotionVO(promotionVOs);
        return promotionVOs;
    }

    private static List<OfferDetailVO> parseOfferDetailInfo(JSONObject jsonObject, OfferTypeJsonVO offerTypeJsonVO) {
        List<OfferDetailVO> offerDetailVOs = new ArrayList<OfferDetailVO>();
        JSONArray offerDetailArray = (JSONArray) jsonObject.get("offerDetail");

        for (Object offerDetail : offerDetailArray) {
            OfferDetailVO offerDetailVO = new OfferDetailVO();
            List<RewardCriteraVO> rewardCriteraVOs = new ArrayList<RewardCriteraVO>();
            List<QualifyCriteriaVO> qualifyCriteriaVOs = new ArrayList<QualifyCriteriaVO>();
            List<String> dpcis = new ArrayList<String>();
            List<Long> deptIds = new ArrayList<Long>();
            List<String> classIds = new ArrayList<String>();
            List<String> itemIds = new ArrayList<String>();

            JSONObject offerDetailObj = (JSONObject) offerDetail;
            offerDetailVO.setOfferDetailId((String) offerDetailObj.get("offerDetailId"));
            JSONArray offerDetailAlternateIds = (JSONArray) offerDetailObj.get("offerDetailAlternateId");
            List<String> alernateIds = new ArrayList<String>();
            List<String> alternateIdSourceSystems = new ArrayList<String>();
            for (Object offerDetailAlternateIdObj : offerDetailAlternateIds) {
                JSONObject aofferDetailAlternateId = (JSONObject) offerDetailAlternateIdObj;
                alernateIds.add((String) aofferDetailAlternateId.get("alternateId"));
                JSONObject alternateIdSourceSystem = (JSONObject) aofferDetailAlternateId.get("alternateIdSourceSystem");
                alternateIdSourceSystems.add((String) alternateIdSourceSystem.get("id"));
            }
            offerDetailVO.setAlternateId(alernateIds);
            offerDetailVO.setAlternateIdSourceSystem(alternateIdSourceSystems);

            offerDetailVO.setOfferDetailDescription((String) offerDetailObj.get("offerDetailDescription"));
            JSONArray qualifyCriteria = (JSONArray) offerDetailObj.get("qualifyCriteria");

            for (Object aQualifyCriteria : qualifyCriteria) {
                JSONObject qualifyCriteriaObj = (JSONObject) aQualifyCriteria;
                QualifyCriteriaVO qualifyCriteriaVO = new QualifyCriteriaVO();
                qualifyCriteriaVO.setCriteriaId((String) qualifyCriteriaObj.get("criteriaId"));

                JSONArray criteriaObjectArray = (JSONArray) qualifyCriteriaObj.get("criteriaObject");

                for (Object criteriaObjVal : criteriaObjectArray) {
                    JSONObject criteriaObj = (JSONObject) criteriaObjVal;
                    JSONArray productObjectIdArray = (JSONArray) criteriaObj.get("productObjectId");

                    for (Object aProductObjectIdArray : productObjectIdArray) {
                        JSONObject tcin = (JSONObject) aProductObjectIdArray;
                        dpcis.add((String) tcin.get("dpci"));
                        JSONObject merchClassificationArray = (JSONObject) tcin.get("merchClassification");
                        if (null != merchClassificationArray) {
                            deptIds.add((Long) merchClassificationArray.get("departmentId"));
                            classIds.add((String) merchClassificationArray.get("classId"));
                            itemIds.add((String) merchClassificationArray.get("itemId"));
                        }
                    }

                    qualifyCriteriaVO.setDpci(dpcis);
                    qualifyCriteriaVO.setDepartmentId(deptIds);
                    qualifyCriteriaVO.setClassId(classIds);
                    qualifyCriteriaVO.setItemId(itemIds);

                }

                JSONObject legacyCriteriaFinancialElements = (JSONObject) qualifyCriteriaObj.get("legacyCriteriaFinancialElements");
                qualifyCriteriaVO.setOfferRetailAmount(String.valueOf(legacyCriteriaFinancialElements.get("offerRetailAmount")));
                qualifyCriteriaVO.setSpecialCostAmount(String.valueOf(legacyCriteriaFinancialElements.get("specialCostAmount")));
                qualifyCriteriaVO.setOriginalBohQuantity((Long) legacyCriteriaFinancialElements.get("originalBohQuantity"));
                qualifyCriteriaVO.setRevisedBohQuantity((Long) legacyCriteriaFinancialElements.get("revisedBohQuantity"));
                qualifyCriteriaVO.setOriginalPicturedBohQuantity((Long) legacyCriteriaFinancialElements.get("originalPicturedBohQuantity"));
                qualifyCriteriaVO.setOriginalPicturedProductQuantity((Long) legacyCriteriaFinancialElements.get("originalPicturedProductQuantity"));
                qualifyCriteriaVO.setRevisedPicturedProductQuantity((Long) legacyCriteriaFinancialElements.get("revisedPicturedProductQuantity"));
                qualifyCriteriaVO.setOriginalUnitQuantity((Long) legacyCriteriaFinancialElements.get("originalUnitQuantity"));
                qualifyCriteriaVO.setRevisedUnitQuantity((Long) legacyCriteriaFinancialElements.get("revisedUnitQuantity"));
                JSONObject productPriceProgram = (JSONObject) legacyCriteriaFinancialElements.get("productPriceProgram");
                qualifyCriteriaVO.setProductPriceProgramCode((String) productPriceProgram.get("code"));

                qualifyCriteriaVO.setHighOfferRetailAmount(String.valueOf(legacyCriteriaFinancialElements.get("highOfferRetailAmount")));
                qualifyCriteriaVO.setLowOfferRetailAmount(String.valueOf(legacyCriteriaFinancialElements.get("lowOfferRetailAmount")));
                qualifyCriteriaVO.setWeightedOfferRetailAmount(String.valueOf(legacyCriteriaFinancialElements.get("weightedOfferRetailAmount")));
                qualifyCriteriaVO.setWeightedRegularRetailAmount(String.valueOf(legacyCriteriaFinancialElements.get("weightedRegularRetailAmount")));

                qualifyCriteriaVOs.add(qualifyCriteriaVO);
            }

            offerDetailVO.setQualifyCriteriaVO(qualifyCriteriaVOs);

            JSONArray rewardCriteria = (JSONArray) offerDetailObj.get("rewardCriteria");

            for (Object aRewardCriteria : rewardCriteria) {
                JSONObject rewardCriteriaobj = (JSONObject) aRewardCriteria;
                RewardCriteraVO rewardCriteraVO = new RewardCriteraVO();
                JSONObject rewardType = (JSONObject) rewardCriteriaobj.get("rewardType");
                rewardCriteraVO.setRewardTypeCode((String) rewardType.get("code"));
                rewardCriteraVO.setRewardValue(String.valueOf(rewardCriteriaobj.get("rewardValue")));

                JSONArray criteriaObjectArray = (JSONArray) rewardCriteriaobj.get("criteriaObject");
                List<String> rewardCriteriaDpcis = new ArrayList<String>();
                List<Long> rewardCriteriaDeptIds = new ArrayList<Long>();
                List<String> rewardCriteriaClassIds = new ArrayList<String>();
                List<String> rewardCriteriaItemIds = new ArrayList<String>();

                for (Object criteriaObjVal : criteriaObjectArray) {
                    JSONObject criteriaObj = (JSONObject) criteriaObjVal;
                    JSONArray productObjectIdArray = (JSONArray) criteriaObj.get("productObjectId");

                    for (Object aProductObjectIdArray : productObjectIdArray) {
                        JSONObject tcin = (JSONObject) aProductObjectIdArray;
                        rewardCriteriaDpcis.add((String) tcin.get("dpci"));
                        JSONObject merchClassificationArray = (JSONObject) tcin.get("merchClassification");
                        rewardCriteriaDeptIds.add((Long) merchClassificationArray.get("departmentId"));
                        rewardCriteriaClassIds.add((String) merchClassificationArray.get("classId"));
                        rewardCriteriaItemIds.add((String) merchClassificationArray.get("itemId"));

                    }

                    rewardCriteraVO.setRewardCriteriaDpci(rewardCriteriaDpcis);
                    rewardCriteraVO.setRewardCriteriaDepartmentId(rewardCriteriaDeptIds);
                    rewardCriteraVO.setRewardCriteriaClassId(rewardCriteriaClassIds);
                    rewardCriteraVO.setRewardCriteriaItemId(rewardCriteriaItemIds);

                }
                rewardCriteraVOs.add(rewardCriteraVO);


            }
            offerDetailVO.setRewardCriteraVO(rewardCriteraVOs);
            JSONObject legacyOfferDetailElements = (JSONObject) offerDetailObj.get("legacyOfferDetailElements");
            offerDetailVO.setLocationObjectQuantity((Long) legacyOfferDetailElements.get("locationObjectQuantity"));
            offerDetailVOs.add(offerDetailVO);

        }
        offerTypeJsonVO.setOfferDetailVO(offerDetailVOs);
        return offerDetailVOs;
    }

    private static OfferTypeJsonVO parseOfferBasicInfo(JSONObject jsonObject) {

        OfferTypeJsonVO offerTypeJsonVO = new OfferTypeJsonVO();
        offerTypeJsonVO.setOfferId((String) jsonObject.get("offerId"));
        JSONArray offerAlternateIdArray = (JSONArray) jsonObject.get("offerAlternateId");
        List<String> aletrnateIdList = new ArrayList<String>();
        List<String> alternateIdSrcSystemList = new ArrayList<String>();
        for (Object offerAlternateId : offerAlternateIdArray) {
            JSONObject offerAlternateIdObj = (JSONObject) offerAlternateId;
            aletrnateIdList.add((String) offerAlternateIdObj.get("alternateId"));
            JSONObject alternateIdSourceSystem = (JSONObject) offerAlternateIdObj.get("alternateIdSourceSystem");
            alternateIdSrcSystemList.add((String) alternateIdSourceSystem.get("id"));
        }
        offerTypeJsonVO.setAleternateId(aletrnateIdList);
        offerTypeJsonVO.setSrcSystemCd(alternateIdSrcSystemList);

        offerTypeJsonVO.setOfferDetailDescription((String) jsonObject.get("offerDescription"));
        offerTypeJsonVO.setStartDateTime((String) jsonObject.get("startDateTime"));
        offerTypeJsonVO.setEndDateTime((String) jsonObject.get("endDateTime"));

        JSONObject conditionSet = (JSONObject) jsonObject.get("conditionSet");
        offerTypeJsonVO.setRaincheckApplicable((Boolean) conditionSet.get("isRaincheckApplicable"));

        JSONObject legacyConditionElements = (JSONObject) conditionSet.get("legacyConditionElements");
        offerTypeJsonVO.setHasGiftWithPurchase((Boolean) legacyConditionElements.get("hasGiftWithPurchase"));

        JSONObject financialDetailSetupLevel = (JSONObject) legacyConditionElements.get("financialDetailSetupLevel");
        offerTypeJsonVO.setFinDetSetUpLvlCode((String) financialDetailSetupLevel.get("code"));
        JSONObject legacyOfferElements = (JSONObject) jsonObject.get("legacyOfferElements");
        offerTypeJsonVO.setIsCompleteForUse((Boolean) legacyOfferElements.get("isCompleteForUse"));
        return offerTypeJsonVO;
    }

}
