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

import java.io.*;
import java.util.Map;


/**
 * Created by a025660 on 2/20/15.
 */
public class KafkaConsumerBolt extends BaseRichBolt {

    OutputCollector _collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {

        String str_json_array=tuple.getString(0);
        JSONParser parser=new JSONParser();
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray)parser.parse(str_json_array);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(int i=0; i<jsonArray.size() ; i++) {
            JSONObject jsonObject=(JSONObject) jsonArray.get(i);
            _collector.emit(tuple,new Values(jsonObject.toString()));
        }
        Logger logger= LoggerFactory.getLogger(KafkaConsumerBolt.class);
        logger.debug("from bolt: JSON Array: " + tuple.getString(0));
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("Json_array.txt",true));
            fileWriter.append("Line: " + tuple.getString(0));
            fileWriter.newLine();
            fileWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        _collector.ack(tuple);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("Individual_JSON"));
    }
}
