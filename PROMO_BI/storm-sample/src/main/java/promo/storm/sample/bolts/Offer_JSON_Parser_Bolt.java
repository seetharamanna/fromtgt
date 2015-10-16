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
import promo.storm.sample.VO.OfferJsonParser;
import promo.storm.sample.VO.OfferTypeJsonVO;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by a025660 on 4/14/2015.
 */
public class Offer_JSON_Parser_Bolt extends BaseRichBolt {
    OutputCollector _collector;


    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
    }


    public void execute(Tuple tuple) {
        Logger logger = LoggerFactory.getLogger(Offer_JSON_Parser_Bolt.class);
        logger.debug("from bolt: JSON individual: " + tuple.getString(0));

        String str_json_obj = tuple.getString(0);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(str_json_obj);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        OfferTypeJsonVO offerTypeJsonVO = OfferJsonParser.parseJSON(jsonObject);

        _collector.emit(tuple, new Values(offerTypeJsonVO));


        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("Json_individual.txt", true));
            fileWriter.append("Line: " + tuple.getString(0));
            fileWriter.newLine();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        _collector.ack(tuple);

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("OfferVOObject"));
    }


}
