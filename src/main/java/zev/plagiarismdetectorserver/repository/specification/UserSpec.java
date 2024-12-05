package zev.plagiarismdetectorserver.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import zev.plagiarismdetectorserver.entity.User;
import zev.plagiarismdetectorserver.entity.enums.Gender;

public class UserSpec {

  public Specification<User> hasFirstName(String firstName) {
    return new Specification<User>() {
      @Override
      public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
          CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%");
      }
    };
  }

  public Specification<User> equalsGender(Gender gender) {
    return new Specification<User>() {
      @Override
      public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
          CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("gender"), gender);
      }
    };
  }
}
