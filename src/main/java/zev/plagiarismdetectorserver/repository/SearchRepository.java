package zev.plagiarismdetectorserver.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import zev.plagiarismdetectorserver.dto.response.PageResponse;
import zev.plagiarismdetectorserver.entity.ClassRoom;
import zev.plagiarismdetectorserver.entity.User;
import zev.plagiarismdetectorserver.repository.criteria.SearchCriteria;
import zev.plagiarismdetectorserver.repository.criteria.SearchCriteriaQueryConsumer;

@Component
public class SearchRepository {

  private final UserRepository userRepository;
  @PersistenceContext
  private EntityManager entityManager;

  public SearchRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public PageResponse<?> searchAdvance(int pageNo, int pageSize, String sortBy, String classroom,
      String[] search) {

    List<SearchCriteria> criteriaList = new ArrayList<>();

    // 1. phan tach cac phan tu ra firstname: value -> firstname | : | value
    if (search != null) {
      for (String s : search) {
        Pattern pattern = Pattern.compile("(\\w+?)([:><])(.*)");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
          criteriaList.add(
              new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
        }

      }
    }

    // 2. lay ra so luong ban ghi
    List<User> users = getUsers(pageNo, pageSize, criteriaList, classroom, sortBy);

    // 3. lay total elements
    Long totalElements = getTotalElements(criteriaList, classroom);

    return PageResponse.builder()
        .pageNo(pageNo)
        .pageSize(pageSize)
        .totalElements(totalElements)
        .items(users)
        .build();
  }

  private List<User> getUsers(int pageNo, int pageSize, List<SearchCriteria> criteriaList,
      String classroom, String sortBy) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
    Root<User> root = criteriaQuery.from(User.class);

    // 2. xu ly cau query
    Predicate predicate = criteriaBuilder.conjunction();

    SearchCriteriaQueryConsumer consumer = new SearchCriteriaQueryConsumer(criteriaBuilder,
        predicate, root);

    // 2. xu ly join
    if (StringUtils.hasLength(classroom)) {
      Join<User, ClassRoom> classRoomJoin = root.join("classRooms", JoinType.INNER);

      Predicate classRoomPredicate = criteriaBuilder.like(classRoomJoin.get("name"),
          "%" + classroom + "%");

      criteriaQuery.where(predicate, classRoomPredicate);
    } else {

      criteriaList.forEach(consumer);

      predicate = consumer.getPredicate();

      criteriaQuery.where(predicate);
    }

    // 3.sort
    if (StringUtils.hasLength(sortBy)) {

      Pattern pattern = Pattern.compile("(\\w+?)(:)(asc|desc)");
      Matcher matcher = pattern.matcher(sortBy);

      if (matcher.find()) {

        String columName = matcher.group(1);

        if (matcher.group(3).equals("desc")) {
          criteriaQuery.orderBy(criteriaBuilder.desc(root.get(columName)));

        } else {
          criteriaQuery.orderBy(criteriaBuilder.asc(root.get(columName)));

        }
      }
    }

    return entityManager.createQuery(criteriaQuery).setFirstResult(pageNo).setMaxResults(pageSize)
        .getResultList();
  }

  private Long getTotalElements(List<SearchCriteria> criteriaList, String classroom) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
    Root<User> root = criteriaQuery.from(User.class);

    // 2. xu ly cau query
    Predicate predicate = criteriaBuilder.conjunction();
    SearchCriteriaQueryConsumer consumer = new SearchCriteriaQueryConsumer(criteriaBuilder,
        predicate, root);

    // 2. xu ly join
    if (StringUtils.hasLength(classroom)) {

      Join<User, ClassRoom> classRoomJoin = root.join("classRooms");
      Predicate classRoomPredicate = criteriaBuilder.like(classRoomJoin.get("name"),
          "%" + classroom + "%");

      criteriaQuery.select(criteriaBuilder.count(root));
      criteriaQuery.where(predicate, classRoomPredicate);

    } else {

      criteriaList.forEach(consumer);
      predicate = consumer.getPredicate();

      criteriaQuery.select(criteriaBuilder.count(root));

      criteriaQuery.where(predicate);
    }

    return entityManager.createQuery(criteriaQuery).getSingleResult();
  }

  public PageResponse<?> searchSpecification(Pageable pageable, String[] classroom, String[] user) {
    Page<User> users = null;
    if (classroom != null && user != null) {
      // tim kiem co join
      users = userRepository.findAll(pageable);
    } else if (classroom == null && user != null) { // tim kiem k can join

    } else {

      userRepository.findAll(pageable);
    }

    return PageResponse.builder()
        .pageNo(pageable.getPageNumber())
        .pageSize(pageable.getPageSize())
        .totalElements(users != null ? users.getTotalElements() : 0)
        .items(users != null ? users.get().toList() : null)
        .build();
  }
}
