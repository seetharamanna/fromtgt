package promo.storm.sample.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import promo.storm.sample.VO.OfferTypeJsonVO;
import promo.storm.sample.VO.PromotionVO;

import java.util.List;
import java.util.Map;

/**
 * Created by a025660 on 6/18/2015.
 */
public class OfferPromotionPrinter extends BaseRichBolt {
    OutputCollector _collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {

        OfferTypeJsonVO offerTypeJsonVO = (OfferTypeJsonVO) tuple.getValue(0);
        List<PromotionVO> promotionVOs;
        promotionVOs = offerTypeJsonVO.getPromotionVO();
        for (PromotionVO promotionVO : promotionVOs) {
            for (int k = 0; k < promotionVO.getAlternateId().size(); k++) {
                _collector.emit(tuple, new Values(
                        offerTypeJsonVO.getOfferId(),
                        promotionVO.getAlternateId().get(k),
                        promotionVO.getAlternateIdSourceSystem().get(k),
                        promotionVO.getPromotionAlternateDescription(),
                        promotionVO.getMediaTypeCode(),
                        promotionVO.getProductRepresentationCode(),
                        promotionVO.getPage(),
                        promotionVO.getPromotionComment(),
                        promotionVO.getCampaignThemeCode(),
                        promotionVO.getContactName(),
                        promotionVO.getIsBrandImagePromotion(),
                        promotionVO.getIsProductSalvaged()
                ));
            }
        }

        _collector.ack(tuple);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(
                "offerid",
                "alternateid",
                "alternateidsourcesystem",
                "promotionalternatedescription",
                "mediatypecode",
                "productrepresentationcode",
                "page",
                "promotioncomment",
                "campaignthemecode",
                "contactname",
                "isbrandimagepromotion",
                "isproductsalvaged"
        ));
    }
}
