package promo.storm.sample.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import promo.storm.sample.VO.OfferTypeJsonVO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

/**
 * Created by a025660 on 4/16/2015.
 */
public class OfferStormHiveBolt extends BaseRichBolt {

    OutputCollector collector;
    Properties topo_properties;

    public OfferStormHiveBolt(Properties topo_properties) {

        this.topo_properties = topo_properties;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        OfferTypeJsonVO vo = (OfferTypeJsonVO) tuple.getValue(0);
        collector.emit(tuple, new Values(vo.getOfferId()));
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("vo_individual.txt", true));
            fileWriter.append("Line: " + vo.getOfferId());
            fileWriter.newLine();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            loadJsonDataToHive(vo);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        collector.ack(tuple);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("ValueObj_attributes"));
    }

    private void loadJsonDataToHive(OfferTypeJsonVO offerTypeJsonVO) throws SQLException {
        Logger logger = LoggerFactory.getLogger(OfferStormHiveBolt.class);

        String driverName = topo_properties.getProperty("hive.driver.name");
        logger.info("read driver name:" + driverName);
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Connection con = DriverManager.getConnection("jdbc:hive2://redlx2001.hq.target.com:8443/a567812;ssl=true?hive.server2.transport.mode=http;hive.server2.thrift.http.path=gateway/bigred/hive", "A567812", "Dravid12347*");
        Statement stmt = con.createStatement();
        logger.info("Connection created...");

        stmt.execute("set hive.support.concurrency=true");
        stmt.execute("set hive.enforce.bucketing=true");
        stmt.execute("set hive.exec.dynamic.partition.mode=nonstrict");
        stmt.execute("set hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DbTxnManager");
        stmt.execute("set hive.compactor.initiator.on=true");
        stmt.execute("set hive.compactor.worker.threads=2");
        logger.info("Parameters set.........");


        String tableName = "promo_storm_hive_table_temp";


        stmt.execute("create table if not exists " + tableName + " (offerId int,offerEvent String,offerType_code String,offerType_name String,offerDescription String,offerAlternateDescription String,mediaType_code String,mediaType_name String,startDateTime String,endDateTime String,cushionTime String,usePriceType_code String,usePriceType_name String,tcin String,discountType_code String,discountType_name String,percentDiscMinimumAmount String,percentDiscMaximumAmount String,locationId String,createUserId String,createDate String,updateDate String)  row format delimited fields terminated by ',' stored as textfile");
        System.out.println("created table.........");
        String sql = null;

//            for(String tcin:offerTypeJsonVO.getTcinValue()) {
//                sql = "insert into table " + tableName + " values(" + offerTypeJsonVO.getOfferid() + ",'" + offerTypeJsonVO.getOfferEvent() + "','" + offerTypeJsonVO.getOfferTypeCode() + "','" + offerTypeJsonVO.getOfferTypeName() + "','" + offerTypeJsonVO.getOfferDescription() + "','" + offerTypeJsonVO.getOfferAlternateDescription() + "','" + offerTypeJsonVO.getMediaTypeCode() + "','" + offerTypeJsonVO.getMediaTypeName() + "','" + offerTypeJsonVO.getStartDateTime() + "','" + offerTypeJsonVO.getEndDateTime() + "','" + offerTypeJsonVO.getCushionTime() + "','" + offerTypeJsonVO.getUsePriceTypeCode() + "','" + offerTypeJsonVO.getUsePriceTypeName() + "','" + tcin + "','" + offerTypeJsonVO.getDiscountTypeCode() + "','" + offerTypeJsonVO.getDiscountTypeName() + "','" + offerTypeJsonVO.getPercentDiscMinimumAmount() + "','" + offerTypeJsonVO.getPercentDiscMaximumAmount() + "','" + offerTypeJsonVO.getLocationId() + "','" + offerTypeJsonVO.getCreateusrId() + "','" + offerTypeJsonVO.getCreatedate() + "','" + offerTypeJsonVO.getUpdateDate() + "')";
//                logger.info("Running: " + sql);
//        stmt.execute(sql);
//        logger.debug("inserted data............ ");
//    }

}
}
