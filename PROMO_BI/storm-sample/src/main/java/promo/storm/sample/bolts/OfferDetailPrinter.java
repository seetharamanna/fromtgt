package promo.storm.sample.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import promo.storm.sample.VO.OfferDetailVO;
import promo.storm.sample.VO.OfferTypeJsonVO;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by a025660 on 6/18/2015.
 */
public class OfferDetailPrinter extends BaseRichBolt{
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
            for (int k = 0; k < offerDetailVO.getAlternateId().size(); k++) {
                _collector.emit(tuple, new Values(
                offerTypeJsonVO.getOfferId() ,
                        offerDetailVO.getOfferDetailId() ,
                        offerDetailVO.getAlternateId().get(k) ,
                        offerDetailVO.getAlternateIdSourceSystem().get(k) ,
                        offerDetailVO.getOfferDetailDescription() ,
                        offerDetailVO.getLocationObjectQuantity()
                ));
            }
        }


        _collector.ack(tuple);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(
                "offerid",
                "offerdetailid",
                "alternateid",
                "alternateidsourcesystem",
                "offerdetaildescription",
                "locationobjectquantity"
        ));
    }
}
