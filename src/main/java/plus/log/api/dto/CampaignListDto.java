package plus.log.api.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import plus.log.api.entity.TCampaignHeaderEntity;
import plus.log.api.entity.VCampaignHeaderAgentEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class CampaignListDto {
    List<TCampaignHeaderEntity> campaignList;
    PageableDto pagination;

    public CampaignListDto(Page<TCampaignHeaderEntity> tCampaignHeaderEntityPage){
        Optional<List<TCampaignHeaderEntity>> ContentOptional = Optional.ofNullable(tCampaignHeaderEntityPage.getContent());
        this.campaignList =ContentOptional.orElse(tCampaignHeaderEntityPage.getContent());
        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageSize(tCampaignHeaderEntityPage.getPageable().getPageSize());
        pageableDto.setPageNumber(tCampaignHeaderEntityPage.getPageable().getPageNumber());
        pageableDto.setTotalPages(tCampaignHeaderEntityPage.getTotalPages());
        pageableDto.setTotalNumberOfRecords(tCampaignHeaderEntityPage.getTotalElements());
        if (tCampaignHeaderEntityPage.hasNext()){
            pageableDto.setHasNext(true);
        }
        this.pagination = pageableDto;

    }
    public CampaignListDto campaignListDtoBroker(Page<TCampaignHeaderEntity> tCampaignHeaderEntityPage){

        List<TCampaignHeaderEntity> campaignListPage = new ArrayList<TCampaignHeaderEntity>();
        if (tCampaignHeaderEntityPage.hasContent()) {
            tCampaignHeaderEntityPage.getContent().forEach(l -> {
                campaignListPage.add(l);
            });
        }
        this.campaignList = campaignListPage;
        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageSize(tCampaignHeaderEntityPage.getPageable().getPageSize());
        pageableDto.setPageNumber(tCampaignHeaderEntityPage.getPageable().getPageNumber());
        pageableDto.setTotalPages(tCampaignHeaderEntityPage.getTotalPages());
        pageableDto.setTotalNumberOfRecords(tCampaignHeaderEntityPage.getTotalElements());
        if (tCampaignHeaderEntityPage.hasNext()){
            pageableDto.setHasNext(true);
        }
        this.pagination = pageableDto;

        return this;

    }

    public CampaignListDto campaignListDtoAgent(Page<VCampaignHeaderAgentEntity> vCampaignHeaderAgentEntityPage){

        List<TCampaignHeaderEntity> campaignListPage = new ArrayList<TCampaignHeaderEntity>();
        if (vCampaignHeaderAgentEntityPage.hasContent()) {
            vCampaignHeaderAgentEntityPage.getContent().forEach(l -> {
                TCampaignHeaderEntity tCampaignHeaderEntity = new TCampaignHeaderEntity();
                tCampaignHeaderEntity.setCampaignHeaderId(l.getCampaignHeaderId());
                tCampaignHeaderEntity.setCampaignCode(l.getCampaignCode());
                tCampaignHeaderEntity.setCampaignNameEng(l.getCampaignNameEng());
                tCampaignHeaderEntity.setCampaignNameZHTW(l.getCampaignNameZHTW());
                tCampaignHeaderEntity.setCampaignNameZHCN(l.getCampaignNameZHCN());
                tCampaignHeaderEntity.setIfaCaIndicator(l.getIfaCaIndicator());
                tCampaignHeaderEntity.setRemark(l.getRemark());
                tCampaignHeaderEntity.setCampaignStartDate(l.getCampaignStartDate());
                tCampaignHeaderEntity.setCampaignEndDate(l.getCampaignEndDate());
                tCampaignHeaderEntity.setThumbnailDocID(l.getThumbnailDocID());
                tCampaignHeaderEntity.setCreatedDate(l.getCreatedDate());
                tCampaignHeaderEntity.setUpdatedDate(l.getUpdatedDate());
                tCampaignHeaderEntity.setCreatedBy(l.getCreatedBy());
                tCampaignHeaderEntity.setUpdatedBy(l.getUpdatedBy());
                campaignListPage.add(tCampaignHeaderEntity);
            });
        }
        this.campaignList = campaignListPage;
        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageSize(vCampaignHeaderAgentEntityPage.getPageable().getPageSize());
        pageableDto.setPageNumber(vCampaignHeaderAgentEntityPage.getPageable().getPageNumber());
        pageableDto.setTotalPages(vCampaignHeaderAgentEntityPage.getTotalPages());
        pageableDto.setTotalNumberOfRecords(vCampaignHeaderAgentEntityPage.getTotalElements());
        if (vCampaignHeaderAgentEntityPage.hasNext()){
            pageableDto.setHasNext(true);
        }
        this.pagination = pageableDto;

        return this;
    }

    public CampaignListDto() {

    }
}
