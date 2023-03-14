package plus.log.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class PCampaignHeaderCommon extends DefaultCommon{

    @Size(min = 1, max=200)
    @Column(name = "campaignCode",nullable = false)
    String campaignCode;

    @Size(min = 1, max=2000)
    @Column(name = "campaignNameEng",nullable = false)
    String campaignNameEng;

    @Size(max=2000)
    @Column(name = "campaignNameZHTW",nullable = true)
    String campaignNameZHTW;

    @Size(max=2000)
    @Column(name = "campaignNameZHCN",nullable = true)
    String campaignNameZHCN;

    @Size(min = 1, max=200)
    @Column(name = "ifaCaIndicator",nullable = false)
    String ifaCaIndicator;

    @Size(max=2000)
    @Column(name = "remark",nullable = true)
    String remark;

    @Column(name = "campaignStartDate",nullable = false)
    Date campaignStartDate;

    @Column(name = "campaignEndDate",nullable = false)
    Date campaignEndDate;

    @Column(name = "thumbnailDocID",nullable = true)
    long thumbnailDocID;


}
