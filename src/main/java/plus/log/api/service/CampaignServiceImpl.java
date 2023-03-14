package plus.log.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import plus.log.api.dto.CampaignListDto;
import plus.log.api.entity.TCampaignHeaderEntity;
import plus.log.api.entity.VCampaignHeaderAgentEntity;
import plus.log.api.parameter.GenericSpecification;
import plus.log.api.parameter.RequestParameter;
import plus.log.api.repository.TCampaignHeaderRepository;
import plus.log.api.repository.VCampaignHeaderAgentRepository;
import plus.log.api.util.QuerySetupUtil;
import org.springframework.data.jpa.domain.Specification;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    TCampaignHeaderRepository tCampaignHeaderRepository;


    @Autowired
    VCampaignHeaderAgentRepository vCampaignHeaderAgentRepository;


    @Override
    public CampaignListDto findCampaignListByCriteria(RequestParameter requestParameter) throws ParseException {
        String parameter = "internal-admin";
        if(requestParameter.getUserParameter().getRole().equals(parameter)){
            return findCampaignListInternalAdminByCriteria (requestParameter);
        } else if ((!requestParameter.getUserParameter().getRole().equals(parameter))&& (requestParameter.getUserParameter().getIfaIdentity().equals("ADMIN"))){
            return findCampaignListInternalAdminByCriteria (requestParameter);
        } else if ((!requestParameter.getUserParameter().getRole().equals(parameter))&& (!requestParameter.getUserParameter().getIfaIdentity().equals("ADMIN"))){
            return findCampaignListAgentByCriteria (requestParameter);
        }
        return null;
    }

    @Override
    public CampaignListDto findCampaignListInternalAdminByCriteria(RequestParameter requestParameter) throws ParseException {
        QuerySetupUtil querySetupUtil = new QuerySetupUtil();
        Pageable pageable = querySetupUtil.setPageable(requestParameter);
        GenericSpecification<?> genericSpecification = querySetupUtil.setCampaignListWhereClause(requestParameter);
        Page<TCampaignHeaderEntity> tCampaignHeaderEntityPage = tCampaignHeaderRepository.findAll((Specification<TCampaignHeaderEntity>) genericSpecification,pageable);
        CampaignListDto campaignListDto = new CampaignListDto(tCampaignHeaderEntityPage);

        return campaignListDto;
    }

    @Override
    public CampaignListDto findCampaignListAgentByCriteria(RequestParameter requestParameter) throws ParseException {

        QuerySetupUtil querySetupUtil = new QuerySetupUtil();
        Pageable pageable = querySetupUtil.setPageable(requestParameter);
        GenericSpecification<?> genericSpecification = querySetupUtil.setCampaignListWhereClause(requestParameter);
        Page<VCampaignHeaderAgentEntity> vCampaignHeaderAgentEntityPage = vCampaignHeaderAgentRepository.findAll((Specification<VCampaignHeaderAgentEntity>) genericSpecification,pageable);
        CampaignListDto campaignListDto = new CampaignListDto();
        campaignListDto.campaignListDtoAgent(vCampaignHeaderAgentEntityPage);

        return campaignListDto;

    }



}
