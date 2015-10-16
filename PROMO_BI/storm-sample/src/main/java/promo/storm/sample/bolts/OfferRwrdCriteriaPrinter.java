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
import promo.storm.sample.VO.RewardCriteraVO;

import java.util.List;
import java.util.Map;

/**
 * Created by a025660 on 6/18/2015.
 */
public class OfferRwrdCriteriaPrinter extends BaseRichBolt {
    OutputCollector _collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {

        OfferTypeJsonVO offerTypeJsonVO=(OfferTypeJsonVO) tuple.getValue(0);
        List<OfferDetailVO> offerDetailVOs;
        offerDetailVOs = offerTypeJsonVO.getOfferDetailVO();
        for (OfferDetailVO offerDetailVO : offerDetailVOs) {
            List<RewardCriteraVO> rewardCriteraVOs;
            rewardCriteraVOs = offerDetailVO.getRewardCriteraVO();
            for(RewardCriteraVO rewardCriteraVO : rewardCriteraVOs){
                for (int k = 0; k < rewardCriteraVO.getRewardCriteriaDepartmentId().size(); k++) {
                    _collector.emit(tuple,new Values(

                    offerTypeJsonVO.getOfferId(),
                            offerDetailVO.getOfferDetailId() ,
                            rewardCriteraVO.getCriteriaId() ,
                            rewardCriteraVO.getRewardTypeCode() ,
                            rewardCriteraVO.getRewardValue() ,
                            rewardCriteraVO.getRewardCriteriaDpci().get(k) ,
                            rewardCriteraVO.getRewardCriteriaDepartmentId().get(k) ,
                            rewardCriteraVO.getRewardCriteriaClassId().get(k) ,
                            rewardCriteraVO.getRewardCriteriaItemId().get(k)
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
                "rewardtypecode",
                "rewardvalue",
                "dpci",
                "departmentid",
                "classid",
                "itemid"
        ));
    }
}
