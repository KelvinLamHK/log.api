package plus.log.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableDto {
    int pageNumber;
    int pageSize;
    int totalPages;
    long totalNumberOfRecords;
    boolean hasNext;
}
