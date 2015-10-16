package promo.storm.sample;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.log4j.Logger;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import org.apache.storm.hdfs.bolt.HdfsBolt;
import promo.storm.sample.bolts.KafkaConsumerBolt;
import promo.storm.sample.bolts.OfferHDFSBolt;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by A025660 on 2/23/2015.
 */
public class StormSampleTopology {

    public static class RandomSentenceSpout extends BaseRichSpout {
        SpoutOutputCollector _collector;
        Random _rand;


        @Override
        public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
            _collector = collector;
            _rand = new Random();
        }

        @Override
        public void nextTuple() {
            Utils.sleep(100);
            String[] sentences = new String[]{"the cow jumped over the moon", "an apple a day keeps the doctor away",
                    "four score and seven years ago", "snow white and the seven dwarfs", "i am at two with nature"};
            String sentence = sentences[_rand.nextInt(sentences.length)];
            _collector.emit(new Values(sentence));
        }

        @Override
        public void ack(Object id) {
        }

        @Override
        public void fail(Object id) {
        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("word"));
        }

    }

    public static void main(String[] args) {


        TopologyBuilder topologyBuilder = new TopologyBuilder();


        topologyBuilder.setSpout("test1 spout", new RandomSentenceSpout(), 2);
        //topologyBuilder.setBolt("test1 bolt", new KafkaConsumerBolt(),3).shuffleGrouping("test1 spout");

        Properties topo_properties = new Properties();
        try {
            topo_properties.load(new FileInputStream("storm-sample/src/main/resources/Topology.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        OfferHDFSBolt offerHDFSBolt = new OfferHDFSBolt();
        HdfsBolt hdfsBolt = offerHDFSBolt.properties(topo_properties);

        topologyBuilder.setBolt("hdfs_bolt", hdfsBolt, 2).shuffleGrouping("test1 spout");

        Config conf = new Config();
        conf.setDebug(true);
        conf.put("hdfs.keytab.file", topo_properties.getProperty("storm.keytab.file"));
        conf.put("hdfs.kerberos.principal", topo_properties.getProperty("storm.kerberos.principal"));
//        conf.put("storm.keytab.file", topo_properties.getProperty("storm.keytab.file"));
//        conf.put("storm.kerberos.principal", topo_properties.getProperty("storm.kerberos.principal"));
        //conf.put("hadoop.security.authentication",topo_properties.getProperty("hadoop.security.authentication"));


        //UserGroupInformation.Auth

        //UserGroupInformation.setAuthenticationMethod(UserGroupInformation.AuthenticationMethod.KERBEROS);


        //added 3/24 to submit in storm cluster
        //KERBEROS
        /*conf.setNumWorkers(5);
        conf.setNumAckers(1);
        conf.setMaxTaskParallelism(10);
        conf.setMaxSpoutPending(2500);
        conf.put(Config.NIMBUS_HOST, "prmlx0030.hq.target.com");
        conf.put(Config.NIMBUS_THRIFT_PORT, 6627);
        conf.put(Config.STORM_ZOOKEEPER_PORT, 2181);
        conf.put(Config.TOPOLOGY_DEBUG, Boolean.parseBoolean("false"));
        conf.put(Config.STORM_ZOOKEEPER_SERVERS, Arrays.asList("prmlx0030.hq.target.com"));*/

       /* //Performance tuning //
        conf.put(Config.TOPOLOGY_RECEIVER_BUFFER_SIZE, Integer.parseInt(topoProperties.getProperty("conf.topology.transfer.buffer_size")));
        conf.put(Config.TOPOLOGY_TRANSFER_BUFFER_SIZE, Integer.parseInt(topoProperties.getProperty("conf.topology.receiver.buffer.size")));
        conf.put(Config.TOPOLOGY_EXECUTOR_RECEIVE_BUFFER_SIZE, Integer.parseInt(topoProperties.getProperty("conf.topology.executor.receive.buffer.size")));
        conf.put(Config.TOPOLOGY_EXECUTOR_SEND_BUFFER_SIZE, Integer.parseInt(topoProperties.getProperty("conf.topology.executor.send.buffer.size")));
        */
        /*Configuration hadoop_conf=new Configuration();
        hadoop_conf.set("hadoop.security.authentication","kerberos");
        UserGroupInformation.setConfiguration(hadoop_conf);


        if (UserGroupInformation.isSecurityEnabled()) {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ SECURITY $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        } else {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ NO-SECURITY $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        }*/
        Logger logger = Logger.getLogger(StormSampleTopology.class);
        String local_mode = "true";
        if (local_mode.equals("true")) {
            LocalCluster cluster = new LocalCluster();

            cluster.submitTopology("test1 topology", conf, topologyBuilder.createTopology());

            Utils.sleep(1000000);

            cluster.killTopology("test1 topology");

            cluster.shutdown();
        } else {
            try {
                logger.debug("inside cluster mode ");
                StormSubmitter.submitTopology("test1 topology", conf, topologyBuilder.createTopology());
                logger.debug("log: submitted to cluster ");
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            }
        }

/*        Properties topology_prop = new Properties();
        try {
            topology_prop.load(new FileInputStream(args[0]));
        }
        catch (FileNotFoundException e1) {
            System.out.println("args[0] is not present");
            e1.printStackTrace();
        }
        catch(IOException e2) {
            e2.printStackTrace();
        }

        //spout settings
        String topic_name = topology_prop.getProperty("kafka.topic.name");
        BrokerHosts hosts = new ZkHosts(topology_prop.getProperty("kafka.host.zookeeper.url"));
        SpoutConfig spoutConfig = new SpoutConfig(hosts,topic_name,"/"+topic_name,topology_prop.getProperty("kafka.group.id"));
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        spoutConfig.forceFromStart = Boolean.parseBoolean(topology_prop.getProperty("kafka.spout.forcefromstart"));
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);

        //bolt settings

        TopologyBuilder topologyBuilder=null;*/

    }


}
