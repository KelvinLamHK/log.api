package plus.log.api.entity;


import lombok.Getter;
import lombok.Setter;
import plus.log.api.parameter.CampaignHeaderParameter;
import plus.log.api.parameter.RequestParameter;
import plus.log.api.parameter.UserParameter;
import plus.log.api.util.StringUtil;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;

@Entity
@Table(name = "TCampaignHeader")
@Getter
@Setter
public class TCampaignHeaderEntity extends PCampaignHeaderCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaignHeaderId")
    private long campaignHeaderId;

    public TCampaignHeaderEntity(){}

    public TCampaignHeaderEntity(RequestParameter requestParameter) throws ParseException {
        CampaignHeaderParameter campaignHeaderParameter = requestParameter.getCampaignHeaderParameter();
        UserParameter userParameter = requestParameter.getUserParameter();
        String format = "yyyy-MM-dd";

        if (campaignHeaderParameter.getCampaignHeaderId() != null){
            this.campaignHeaderId = Long.parseLong(campaignHeaderParameter.getCampaignHeaderId());
        }

        if (campaignHeaderParameter.getCampaignCode() != null) {
            this.campaignCode = campaignHeaderParameter.getCampaignCode();
        }

        if (campaignHeaderParameter.getCampaignNameEng() != null){
            this.campaignNameEng = campaignHeaderParameter.getCampaignNameEng();
        }

        if (campaignHeaderParameter.getCampaignNameZHTW() != null){
            this.campaignNameZHTW = campaignHeaderParameter.getCampaignNameZHTW();
        }

        if (campaignHeaderParameter.getCampaignNameZHCN() != null){
            this.campaignNameZHCN = campaignHeaderParameter.getCampaignNameZHCN();
        }

        if (campaignHeaderParameter.getIfaCaIndicator() != null){
            this.ifaCaIndicator = campaignHeaderParameter.getIfaCaIndicator();
        }

        if (campaignHeaderParameter.getThumbnailDocID() != null){
            this.thumbnailDocID = Long.parseLong(campaignHeaderParameter.getThumbnailDocID());
        }

        if (campaignHeaderParameter.getRemark() != null) {
            this.remark = campaignHeaderParameter.getRemark();
        }

        if (campaignHeaderParameter.getCampaignStartDate() != null){
            this.campaignStartDate = StringUtil.stringToDate(campaignHeaderParameter.getCampaignStartDate(), format);
        }

        if (campaignHeaderParameter.getCampaignEndDate() != null){
            this.campaignEndDate = StringUtil.stringToDate(campaignHeaderParameter.getCampaignEndDate(),format);
        }

        if(userParameter.getLoginName() != null){
            this.createdBy = userParameter.getLoginName();
        }

        if(userParameter.getLoginName() != null){
            this.updatedBy = userParameter.getLoginName();
        }

        this.createdDate = new Date();
        this.updatedDate = new Date();

    }

}
