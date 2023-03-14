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
public class DefaultCommon {

    @Column(name = "createdDate",nullable = false)
    Date createdDate;

    @Column(name = "updatedDate",nullable = false)
    Date updatedDate;

    @Size(min = 1, max=200)
    @Column(name = "createdBy",nullable = false)
    String createdBy;

    @Size(min = 1, max=200)
    @Column(name = "updatedBy",nullable = false)
    String updatedBy;
}
