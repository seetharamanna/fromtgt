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
import promo.storm.sample.VO.OfferTypeJsonVO;

import java.io.*;
import java.util.Map;

/**
 * Created by a025660 on 6/17/2015.
 */
public class OfferHeaderPrinter extends BaseRichBolt {

    OutputCollector _collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {

        OfferTypeJsonVO offerTypeJsonVO = (OfferTypeJsonVO) tuple.getValue(0);
        for (int k = 0; k < offerTypeJsonVO.getAleternateId().size(); k++) {

            _collector.emit(tuple, new Values(offerTypeJsonVO.getOfferId(),
                    offerTypeJsonVO.getAleternateId().get(k),
                    offerTypeJsonVO.getSrcSystemCd().get(k),
                    offerTypeJsonVO.getOfferDetailDescription(),
                    offerTypeJsonVO.getStartDateTime(),
                    offerTypeJsonVO.getEndDateTime(),
                    offerTypeJsonVO.isRaincheckApplicable(),
                    offerTypeJsonVO.isHasGiftWithPurchase(),
                    offerTypeJsonVO.getFinDetSetUpLvlCode(),
                    offerTypeJsonVO.getIsCompleteForUse()));
        }

        _collector.ack(tuple);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(
                "offerid",
                "alternateid",
                "srcsystemcd",
                "offerdetaildescription",
                "startdatetime",
                "enddatetime",
                "israincheckapplicable",
                "hasgiftwithpurchase",
                "findetsetuplvlcode",
                "iscompleteforuse"
        ));
    }
}

