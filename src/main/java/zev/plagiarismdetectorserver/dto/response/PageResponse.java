package zev.plagiarismdetectorserver.dto.response;

import lombok.Builder;
import lombok.Setter;


@Builder
@Setter
public class PageResponse<T> {

  private int pageNo;

  private int pageSize;

  private long totalElements;

  private T items;
}
