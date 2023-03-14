package plus.log.api.service;

import plus.log.api.dto.CampaignListDto;
import plus.log.api.parameter.RequestParameter;

import java.text.ParseException;

public interface CampaignService {

       CampaignListDto findCampaignListByCriteria (RequestParameter requestParameter) throws ParseException;

       CampaignListDto findCampaignListInternalAdminByCriteria (RequestParameter requestParameter) throws ParseException;

       CampaignListDto findCampaignListAgentByCriteria(RequestParameter requestParameter) throws ParseException;


}

