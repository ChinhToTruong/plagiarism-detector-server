package zev.plagiarismdetectorserver.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PageResponse <T>{
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private T items;
}
