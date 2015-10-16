package promo.storm.sample;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.storm.hdfs.bolt.HdfsBolt;
import org.apache.storm.hive.bolt.HiveBolt;
import org.apache.storm.hive.bolt.mapper.DelimitedRecordHiveMapper;
import org.apache.storm.hive.bolt.mapper.JsonRecordHiveMapper;
import org.apache.storm.hive.common.HiveOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import promo.storm.sample.bolts.*;
import storm.kafka.*;
import storm.kafka.trident.TridentKafkaState;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by a025660 on 2/20/15.
 */
public class KafkaConsumerTopology {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(KafkaConsumerTopology.class);

/*
        Logger logger = LoggerFactory.getLogger(OfferStormHiveBolt.class);

        String driverName = topo_properties.getProperty("hive.driver.name");
        logger.info("read driver name:"+driverName);
*/

        Properties topo_properties = new Properties();
        try {
            topo_properties.load(new FileInputStream(args[0]));
            //topo_properties.load(new FileInputStream("storm-sample/src/main/resources/Topology.properties"));
        } catch (IOException e) {
            logger.error("Error" + e);
            e.printStackTrace();
            System.exit(2);
        }


        TopologyBuilder topologyBuilder = new TopologyBuilder();

        BrokerHosts hosts = new ZkHosts(topo_properties.getProperty("kafka.zookeeper.conn.string"));
        SpoutConfig spoutConfig = new SpoutConfig(hosts, topo_properties.getProperty("kafka.topic.name"),
                "/" + topo_properties.getProperty("kafka.topic.name"), topo_properties.getProperty("kafka.topic.id"));
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        spoutConfig.forceFromStart = Boolean.parseBoolean(topo_properties.getProperty("kafka.force.FromStart"));
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);

        topologyBuilder.setSpout("kafka_spout", kafkaSpout,
                Integer.parseInt(topo_properties.getProperty("topology.spout.parallelism")));

        topologyBuilder.setBolt("json_parser_bolt", new Offer_JSON_Parser_Bolt(), 2).shuffleGrouping("kafka_spout");
        topologyBuilder.setBolt("offer_header_printer", new OfferHeaderPrinter(), 2).shuffleGrouping("json_parser_bolt");
        topologyBuilder.setBolt("offer_detail_printer", new OfferDetailPrinter(), 2).shuffleGrouping("json_parser_bolt");
        topologyBuilder.setBolt("offer_promo_printer", new OfferPromotionPrinter(), 2).shuffleGrouping("json_parser_bolt");
        topologyBuilder.setBolt("offer_qualify_criteria_printer", new OfferQfyCriteriaPrinter(), 2).shuffleGrouping("json_parser_bolt");
        topologyBuilder.setBolt("offer_reward_criteria_printer", new OfferRwrdCriteriaPrinter(), 2).shuffleGrouping("json_parser_bolt");

        OfferHDFSBolt offerHDFSBolt = new OfferHDFSBolt();
        HdfsBolt hdfsBolt = offerHDFSBolt.properties(topo_properties);

        topologyBuilder.setBolt("hdfs_bolt", hdfsBolt, 2).shuffleGrouping("kafka_spout");

        //setting up Hive
        String metaStoreURI = topo_properties.getProperty("hive.fields.bolt.metastoreuri");
        String tblName1 = topo_properties.getProperty("hive.fields.bolt.offerheader_tablename");
        String dbName = topo_properties.getProperty("hive.fields.bolt.dbname");
        String[] colNames1 = topo_properties.getProperty("hive.fields.bolt.offerheader.colnames").split(",");

        DelimitedRecordHiveMapper mapper = new DelimitedRecordHiveMapper()
                .withColumnFields(new Fields(colNames1));
        HiveOptions hiveOptions = new HiveOptions(metaStoreURI, dbName, tblName1, mapper)
                .withTxnsPerBatch(Integer.parseInt(topo_properties.getProperty("hive.bolt.txns.per.batch")))
                .withBatchSize(Integer.parseInt(topo_properties.getProperty("hive.bolt.batch.size")))
                .withIdleTimeout(Integer.parseInt(topo_properties.getProperty("hive.bolt.idle.timeout")))
                .withKerberosKeytab(topo_properties.getProperty("storm.keytab.file"))
                .withKerberosPrincipal(topo_properties.getProperty("storm.kerberos.principal"));
        topologyBuilder.setBolt("HiveBoltOfferHeader", new HiveBolt(hiveOptions)).shuffleGrouping("offer_header_printer");

        String tblName2 = topo_properties.getProperty("hive.fields.bolt.offerdetail_tablename");
        String[] colNames2 = topo_properties.getProperty("hive.fields.bolt.offerdetail.colnames").split(",");
        DelimitedRecordHiveMapper mapper2 = new DelimitedRecordHiveMapper()
                .withColumnFields(new Fields(colNames2));
        HiveOptions hiveOptions2 = new HiveOptions(metaStoreURI, dbName, tblName2, mapper2)
                .withTxnsPerBatch(Integer.parseInt(topo_properties.getProperty("hive.bolt.txns.per.batch")))
                .withBatchSize(Integer.parseInt(topo_properties.getProperty("hive.bolt.batch.size")))
                .withIdleTimeout(Integer.parseInt(topo_properties.getProperty("hive.bolt.idle.timeout")))
                .withKerberosKeytab(topo_properties.getProperty("storm.keytab.file"))
                .withKerberosPrincipal(topo_properties.getProperty("storm.kerberos.principal"));
        topologyBuilder.setBolt("HiveBoltOfferDetail", new HiveBolt(hiveOptions2)).shuffleGrouping("offer_detail_printer");

        String tblName3 = topo_properties.getProperty("hive.fields.bolt.offerqfy_tablename");
        String[] colNames3 = topo_properties.getProperty("hive.fields.bolt.offerqfy.colnames").split(",");
        DelimitedRecordHiveMapper mapper3 = new DelimitedRecordHiveMapper()
                .withColumnFields(new Fields(colNames3));
        HiveOptions hiveOptions3 = new HiveOptions(metaStoreURI, dbName, tblName3, mapper3)
                .withTxnsPerBatch(Integer.parseInt(topo_properties.getProperty("hive.bolt.txns.per.batch")))
                .withBatchSize(Integer.parseInt(topo_properties.getProperty("hive.bolt.batch.size")))
                .withIdleTimeout(Integer.parseInt(topo_properties.getProperty("hive.bolt.idle.timeout")))
                .withKerberosKeytab(topo_properties.getProperty("storm.keytab.file"))
                .withKerberosPrincipal(topo_properties.getProperty("storm.kerberos.principal"));
        topologyBuilder.setBolt("HiveBoltOfferQlfy",
                new HiveBolt(hiveOptions3)).shuffleGrouping("offer_qualify_criteria_printer");

        String tblName4 = topo_properties.getProperty("hive.fields.bolt.offerrwd_tablename");
        String[] colNames4 = topo_properties.getProperty("hive.fields.bolt.offerrwd.colnames").split(",");
        DelimitedRecordHiveMapper mapper4 = new DelimitedRecordHiveMapper()
                .withColumnFields(new Fields(colNames4));
        HiveOptions hiveOptions4 = new HiveOptions(metaStoreURI, dbName, tblName4, mapper4)
                .withTxnsPerBatch(Integer.parseInt(topo_properties.getProperty("hive.bolt.txns.per.batch")))
                .withBatchSize(Integer.parseInt(topo_properties.getProperty("hive.bolt.batch.size")))
                .withIdleTimeout(Integer.parseInt(topo_properties.getProperty("hive.bolt.idle.timeout")))
                .withKerberosKeytab(topo_properties.getProperty("storm.keytab.file"))
                .withKerberosPrincipal(topo_properties.getProperty("storm.kerberos.principal"));
        topologyBuilder.setBolt("HiveBoltOfferRwd",
                new HiveBolt(hiveOptions4)).shuffleGrouping("offer_reward_criteria_printer");

        String tblName5 = topo_properties.getProperty("hive.fields.bolt.offerprm_tablename");
        String[] colNames5 = topo_properties.getProperty("hive.fields.bolt.offerprm.colnames").split(",");
        DelimitedRecordHiveMapper mapper5 = new DelimitedRecordHiveMapper()
                .withColumnFields(new Fields(colNames5));
        HiveOptions hiveOptions5 = new HiveOptions(metaStoreURI, dbName, tblName5, mapper5)
                .withTxnsPerBatch(Integer.parseInt(topo_properties.getProperty("hive.bolt.txns.per.batch")))
                .withBatchSize(Integer.parseInt(topo_properties.getProperty("hive.bolt.batch.size")))
                .withIdleTimeout(Integer.parseInt(topo_properties.getProperty("hive.bolt.idle.timeout")))
                .withKerberosKeytab(topo_properties.getProperty("storm.keytab.file"))
                .withKerberosPrincipal(topo_properties.getProperty("storm.kerberos.principal"));
        topologyBuilder.setBolt("HiveBoltOfferPromo", new HiveBolt(hiveOptions5)).shuffleGrouping("offer_promo_printer");

        Config conf = new Config();
        //set producer properties.
        Properties props = new Properties();
        props.put("metadata.broker.list", topo_properties.getProperty("kafka.metadata.broker.list"));
        props.put("request.required.acks", Integer.parseInt(topo_properties.getProperty("kafka.request.required.acks")));
        props.put("serializer.class", "kafka.serializer.StringEncoder");

        conf.put(TridentKafkaState.KAFKA_BROKER_PROPERTIES, props);


        //configurations for storm cluster
        conf.setDebug(true);
        conf.setNumWorkers(Integer.parseInt(topo_properties.getProperty("storm.workers")));
        conf.setNumAckers(Integer.parseInt(topo_properties.getProperty("storm.ackers")));
        conf.setMaxTaskParallelism(Integer.parseInt(topo_properties.getProperty("storm.max.tax.parallelism")));
        conf.setMaxSpoutPending(Integer.parseInt(topo_properties.getProperty("storm.max.spout.pending")));
        conf.put(Config.NIMBUS_HOST, topo_properties.getProperty("storm.nimbus.host"));
        conf.put(Config.NIMBUS_THRIFT_PORT, Integer.parseInt(topo_properties.getProperty("storm.nimbus.thrift.port")));
        conf.put(Config.STORM_ZOOKEEPER_PORT, Integer.parseInt(topo_properties.getProperty("storm.zookeeper.port")));
        conf.put(Config.TOPOLOGY_DEBUG, Boolean.parseBoolean(topo_properties.getProperty("storm.topology.debug")));
        conf.put(Config.STORM_ZOOKEEPER_SERVERS, Arrays.asList(topo_properties.getProperty("storm.zookeeper.servers")));

       /* //Performance tuning //
        conf.put(Config.TOPOLOGY_RECEIVER_BUFFER_SIZE, Integer.parseInt(topoProperties.getProperty("conf.topology.transfer.buffer_size")));
        conf.put(Config.TOPOLOGY_TRANSFER_BUFFER_SIZE, Integer.parseInt(topoProperties.getProperty("conf.topology.receiver.buffer.size")));
        conf.put(Config.TOPOLOGY_EXECUTOR_RECEIVE_BUFFER_SIZE, Integer.parseInt(topoProperties.getProperty("conf.topology.executor.receive.buffer.size")));
        conf.put(Config.TOPOLOGY_EXECUTOR_SEND_BUFFER_SIZE, Integer.parseInt(topoProperties.getProperty("conf.topology.executor.send.buffer.size")));
        */
        //conf.put("hdfs.keytab.file",topo_properties.getProperty("storm.keytab.file"));
        //conf.put("hdfs.kerberos.principal",topo_properties.getProperty("storm.kerberos.principal"));
        //conf.put("hadoop.security.authentication",topo_properties.getProperty("hadoop.security.authentication"));

        conf.put("storm.keytab.file", topo_properties.getProperty("storm.keytab.file"));
        conf.put("storm.kerberos.principal", topo_properties.getProperty("storm.kerberos.principal"));

        conf.put("hdfs.keytab.file", topo_properties.getProperty("storm.keytab.file"));
        conf.put("hdfs.kerberos.principal", topo_properties.getProperty("storm.kerberos.principal"));
        conf.put("hadoop.security.authentication", topo_properties.getProperty("hadoop.security.authentication"));

        Map<String, String> map = new HashMap<String, String>();
        map.put("hdfs.keytab.file", topo_properties.getProperty("storm.keytab.file"));
        map.put("hdfs.kerberos.principal", topo_properties.getProperty("storm.kerberos.principal"));
        map.put("hadoop.security.authentication", topo_properties.getProperty("hadoop.security.authentication"));

        conf.put("hadoop_map_conf", map);

        Configuration hadoop_conf = new Configuration();
        hadoop_conf.set("hadoop.security.authentication", "kerberos");
        //hadoop_conf.set("hadoop_map_conf",map);
        UserGroupInformation.setConfiguration(hadoop_conf);


        if (UserGroupInformation.isSecurityEnabled()) {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ SECURITY $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        } else {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ NO-SECURITY $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        }
        if (Boolean.parseBoolean(topo_properties.getProperty("storm.local.mode"))) {
            logger.debug("storm local mode");
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("Promo_BI_local_topology", conf, topologyBuilder.createTopology());
            Utils.sleep(1000000);
            cluster.killTopology("Promo_BI_local_topology");
            cluster.shutdown();
        } else {
            try {
                logger.debug("Storm cluster mode");
                StormSubmitter.submitTopology("Promo_BI_topology", conf, topologyBuilder.createTopology());
                logger.debug("Promo_BI_topology: submitted to cluster ");
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            }
        }

    }
}
