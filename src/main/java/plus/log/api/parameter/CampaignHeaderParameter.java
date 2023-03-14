package plus.log.api.parameter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignHeaderParameter {
    private String campaignHeaderId;
    private String campaignCode;
    private String campaignNameEng;
    private String campaignNameZHTW;
    private String campaignNameZHCN;
    private String ifaCaIndicator;
    private String remark;
    private String thumbnailDocID;
    private String campaignStartDate;
    private String campaignEndDate;

}
