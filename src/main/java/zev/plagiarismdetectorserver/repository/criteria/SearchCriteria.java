package zev.plagiarismdetectorserver.repository.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {

  private String key;  // cac truong du lieu: firstname, lastname, ...
  private String operator; // toan tu: =, >, <
  private Object value; // gia tri search
}
