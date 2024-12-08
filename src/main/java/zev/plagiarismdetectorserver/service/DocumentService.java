package zev.plagiarismdetectorserver.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import zev.plagiarismdetectorserver.dto.request.DocumentCreateRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateDocumentRequest;
import zev.plagiarismdetectorserver.entity.Document;

public interface DocumentService {

  String addDocument(DocumentCreateRequest document);

  void updateDocument(UpdateDocumentRequest request, String documentId);

  void deleteDocument(String documentId);

  Document getDocument(String documentId);

  List<Document> getDocuments(Pageable pageable);

  void addUserReportClassToDocument(String userId, String reportId, String classId,
      String documentId);
}
