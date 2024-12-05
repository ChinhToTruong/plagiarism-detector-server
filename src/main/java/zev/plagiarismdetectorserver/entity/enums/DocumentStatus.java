package zev.plagiarismdetectorserver.entity.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DocumentStatus {

  @JsonProperty("pending")
  PENDING,

  @JsonProperty("success")
  SUCCESS,

  @JsonProperty("reject")
  REJECT,
}
