package promo.storm.sample.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import promo.storm.sample.VO.OfferDetailVO;
import promo.storm.sample.VO.OfferTypeJsonVO;
import promo.storm.sample.VO.QualifyCriteriaVO;

import java.util.List;
import java.util.Map;

/**
 * Created by a025660 on 6/18/2015.
 */
public class OfferQfyCriteriaPrinter extends BaseRichBolt {
    OutputCollector _collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {

        OfferTypeJsonVO offerTypeJsonVO = (OfferTypeJsonVO) tuple.getValue(0);
        List<OfferDetailVO> offerDetailVOs;
        offerDetailVOs = offerTypeJsonVO.getOfferDetailVO();
        for (OfferDetailVO offerDetailVO : offerDetailVOs) {
            List<QualifyCriteriaVO> qualifyCriteriaVOs;
            qualifyCriteriaVOs = offerDetailVO.getQualifyCriteriaVO();
            for (QualifyCriteriaVO qualifyCriteriaVO : qualifyCriteriaVOs) {
                for (int k = 0; k < qualifyCriteriaVO.getDepartmentId().size(); k++) {
                    _collector.emit(tuple, new Values(
                            offerTypeJsonVO.getOfferId(),
                            offerDetailVO.getOfferDetailId(),
                            qualifyCriteriaVO.getCriteriaId(),
                            qualifyCriteriaVO.getDpci().get(k),
                            qualifyCriteriaVO.getDepartmentId().get(k),
                            qualifyCriteriaVO.getClassId().get(k),
                            qualifyCriteriaVO.getItemId().get(k),
                            qualifyCriteriaVO.getOfferRetailAmount(),
                            qualifyCriteriaVO.getSpecialCostAmount(),
                            qualifyCriteriaVO.getOriginalBohQuantity(),
                            qualifyCriteriaVO.getRevisedBohQuantity(),
                            qualifyCriteriaVO.getOriginalPicturedBohQuantity(),
                            qualifyCriteriaVO.getOriginalPicturedProductQuantity(),
                            qualifyCriteriaVO.getRevisedPicturedProductQuantity(),
                            qualifyCriteriaVO.getOriginalUnitQuantity(),
                            qualifyCriteriaVO.getRevisedUnitQuantity(),
                            qualifyCriteriaVO.getProductPriceProgramCode(),
                            qualifyCriteriaVO.getHighOfferRetailAmount(),
                            qualifyCriteriaVO.getLowOfferRetailAmount(),
                            qualifyCriteriaVO.getWeightedOfferRetailAmount(),
                            qualifyCriteriaVO.getWeightedRegularRetailAmount()
                    ));
                }
            }
        }

        _collector.ack(tuple);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(

                "offerid",
                "offerdetailid",
                "criteriaid",
                "dpci",
                "departmentid",
                "classid",
                "itemid",
                "offerretailamount",
                "specialcostamount",
                "originalbohquantity",
                "revisedbohquantity",
                "originalpicturedbohquantity",
                "originalpicturedproductquantity",
                "revisedpicturedproductquantity",
                "originalunitquantity",
                "revisedunitquantity",
                "productpriceprogramcode",
                "highofferretailamount",
                "lowofferretailamount",
                "weightedofferretailamount",
                "weightedregularretailamount"
        ));
    }
}
