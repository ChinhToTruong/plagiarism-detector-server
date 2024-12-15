package zev.plagiarismdetectorserver.dto.response;

import java.io.Serializable;
import lombok.Builder;
import lombok.Setter;


@Builder
@Setter
public class PageResponse<T> implements Serializable {

  private int pageNo;

  private int pageSize;

  private long totalElements;

  private T items;
}
