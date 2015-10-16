package promo.storm.sample.bolts;

import org.apache.storm.hdfs.bolt.HdfsBolt;
import org.apache.storm.hdfs.bolt.format.DefaultFileNameFormat;
import org.apache.storm.hdfs.bolt.format.DelimitedRecordFormat;
import org.apache.storm.hdfs.bolt.format.FileNameFormat;
import org.apache.storm.hdfs.bolt.format.RecordFormat;
import org.apache.storm.hdfs.bolt.rotation.FileRotationPolicy;
import org.apache.storm.hdfs.bolt.rotation.FileSizeRotationPolicy;
import org.apache.storm.hdfs.bolt.sync.CountSyncPolicy;
import org.apache.storm.hdfs.bolt.sync.SyncPolicy;

import java.util.Properties;

/**
 * Created by a025660 on 4/19/2015.
 */
public class OfferHDFSBolt {
    public HdfsBolt properties(Properties topoProperties) {
        //write delimited fields to HDFS
        RecordFormat fieldsFormat = new DelimitedRecordFormat().withFieldDelimiter(topoProperties.getProperty("hdfs.fields.bolt.field.delimiter"));
        SyncPolicy fieldsSyncPolicy = new CountSyncPolicy(Integer.parseInt(topoProperties.getProperty("hdfs.fields.bolt.countsyncpolicy")));
        FileRotationPolicy fieldsRotationPolicy = new FileSizeRotationPolicy(
                Float.parseFloat(topoProperties.getProperty("hdfs.fields.bolt.filesizerotationpolicy.mb")),
                FileSizeRotationPolicy.Units.MB);
        FileNameFormat fieldsFileNameFormat = new DefaultFileNameFormat().withPath(topoProperties.getProperty("hdfs.fields.bolt.output.directory"));

        HdfsBolt hdfsBolt = new HdfsBolt()
                .withFsUrl(topoProperties.getProperty("hdfs.fields.bolt.namenode.address"))
                .withFileNameFormat(fieldsFileNameFormat)
                .withRecordFormat(fieldsFormat)
                .withRotationPolicy(fieldsRotationPolicy)
                .withConfigKey("hadoop_map_conf")
                .withSyncPolicy(fieldsSyncPolicy);
        return  hdfsBolt;
    }
}
