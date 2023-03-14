package plus.log.api.parameter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestParameter {


    PageableParameter pageableParameter;

    UserParameter userParameter;
    CampaignListParameter campaignListParameter;
    CampaignHeaderParameter campaignHeaderParameter;


}
