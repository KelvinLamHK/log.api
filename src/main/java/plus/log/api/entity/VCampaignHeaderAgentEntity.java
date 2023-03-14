package plus.log.api.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VCampaignHeaderAgent")
@Getter
@Setter
@Immutable
public class VCampaignHeaderAgentEntity extends PCampaignHeaderCommon{
    @Id
    @Column(name = "campaignHeaderId")
    private long campaignHeaderId;

    @Column(name = "servAgentCode",nullable = false)
    private String servAgentCode;

    @Column(name = "trCode",nullable = false)
    private String trCode;
}
