package zev.plagiarismdetectorserver.service.impl;


import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zev.plagiarismdetectorserver.dto.request.DocumentCreateRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateDocumentRequest;
import zev.plagiarismdetectorserver.entity.Document;
import zev.plagiarismdetectorserver.exception.DocumentExited;
import zev.plagiarismdetectorserver.exception.DocumentNotFound;
import zev.plagiarismdetectorserver.repository.DocumentRepository;
import zev.plagiarismdetectorserver.service.ClassRoomService;
import zev.plagiarismdetectorserver.service.DocumentService;
import zev.plagiarismdetectorserver.service.PlagiarismService;
import zev.plagiarismdetectorserver.service.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {

  private final DocumentRepository documentRepository;
  private final PlagiarismService plagiarismService;
  private final UserService userService;
  private final ClassRoomService classRoomService;

  @Transactional
  @Override
  public String addDocument(DocumentCreateRequest request) {
    log.info("Add document request: {}", request);

    boolean isDocumentExited = documentRepository.findByTitle(request.getTitle()).isPresent();

    if (isDocumentExited) {
      throw new DocumentExited();
    }

    // use kafka call save file service to get link
    String url = "link to document";

    try {
      Document document = Document.builder()
          .title(request.getTitle())
          .description(request.getDescription())
          .url(url)
          .build();

      var documentId = documentRepository.save(document).getId();

      log.info("Document {} created", document.getTitle());
      return documentId;
    } catch (Exception e) {
      log.error("add document error");
      throw e;
    }
  }

  @Transactional
  @Override
  public void updateDocument(UpdateDocumentRequest request, String documentId) {
    log.info("Update document request: {}", request);

    Document document = findById(documentId);

    if (request.getTitle() != null) {
      document.setTitle(request.getTitle());
    }

    if (request.getDescription() != null) {
      document.setDescription(request.getDescription());
    }

    try {

      documentRepository.save(document);

      log.info("Document {} updated", document.getTitle());

    } catch (Exception e) {

      log.error("update document error");

      throw e;
    }
  }

  @Transactional
  @Override
  public void deleteDocument(String documentId) {
    log.info("Delete document request: {}", documentId);

    Document document = findById(documentId);

    try {
      documentRepository.delete(document);

      log.info("Document {} deleted", document.getTitle());

    } catch (Exception e) {

      log.error("delete document error");

      throw e;
    }
  }

  @Override
  public Document getDocument(String documentId) {
    log.info("Get document request: {}", documentId);
    return findById(documentId);
  }

  @Override
  public List<Document> getDocuments(Pageable pageable) {
    log.info("Get documents request: {}", pageable);

    List<Document> documentList = documentRepository.findAll(pageable).toList();

    return documentList;
  }

  @Override
  public void addUserReportClassToDocument(String userId, String reportId, String classId,
      String documentId) {

    log.info("Add user report class to document request: {}", reportId);

    Document document = findById(documentId);

    document.setReportId(reportId);
    document.setClassRoom(classRoomService.getClassRoom(classId));

    try {

      documentRepository.save(document);

      log.info("Document {} added to classroom: {} with report: {}", document.getTitle(), classId,
          reportId);

    } catch (Exception e) {

      log.error("add user report class error");

      throw e;
    }

    // kafka userservice save document
  }

  private Document findById(String documentId) {
    log.info("Find document by id request: {}", documentId);
    return documentRepository.findById(documentId).orElseThrow(DocumentNotFound::new);
  }

}
