package plus.log.api.util;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import plus.log.api.parameter.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class QuerySetupUtil {

    String dateInString = "yyyy-MM-dd";
    String servAgentCode = "servAgentCode";

    public Pageable setPageable (RequestParameter requestParameter){
        Pageable pageable = null;

        if (requestParameter.getPageableParameter().getOrderBy() == null || requestParameter.getPageableParameter().getOrderSequence() == null ){
            pageable = PageRequest
                    .of(requestParameter.getPageableParameter().getPageNumber()
                            , requestParameter.getPageableParameter().getPageSize());
        } else {
            if (requestParameter.getPageableParameter().getOrderSequence().equals("desc") ){
                pageable = PageRequest
                        .of(requestParameter.getPageableParameter().getPageNumber()
                                , requestParameter.getPageableParameter().getPageSize()
                                , Sort.by(requestParameter.getPageableParameter().getOrderBy()).descending());
            } else {
                pageable = PageRequest
                        .of(requestParameter.getPageableParameter().getPageNumber()
                                , requestParameter.getPageableParameter().getPageSize()
                                , Sort.by(requestParameter.getPageableParameter().getOrderBy()).ascending());
            }
        }


        return pageable;
    }



    public GenericSpecification<Object> setCampaignListWhereClause (RequestParameter requestParameter) throws ParseException {

        GenericSpecification<?> genericSpecification = new GenericSpecification<>();
        var userParameter = requestParameter.getUserParameter();
        CampaignListParameter campaignParameter = requestParameter.getCampaignListParameter();


        if (campaignParameter.getCampaignCode() != null)
            genericSpecification.add(new SearchCriteria("campaignCode", campaignParameter.getCampaignCode(), SearchOperation.MATCH));

        if (campaignParameter.getCampaignName() != null){
            genericSpecification.add(new SearchCriteria("campaignNameEng;campaignNameZHTW;campaignNameZHCN", campaignParameter.getCampaignName(), SearchOperation.GROUP_OR));
        }


        // for external user
        if (!requestParameter.getUserParameter().getRole().equals("internal-admin")){
            genericSpecification.add(new SearchCriteria(servAgentCode, userParameter.getBrokerCode(), SearchOperation.EXIST_CAMPAIGN));
            // for broker agent user
            if(!requestParameter.getUserParameter().getIfaIdentity().equals("ADMIN") ){
                genericSpecification.add(new SearchCriteria("trCode", requestParameter.getUserParameter().getLoginName(), SearchOperation.EQUAL));
            }
        }
        return (GenericSpecification<Object>) genericSpecification;

    }

}
