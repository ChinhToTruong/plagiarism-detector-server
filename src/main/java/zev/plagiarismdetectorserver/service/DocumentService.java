package zev.plagiarismdetectorserver.service;

import org.springframework.data.domain.Pageable;
import zev.plagiarismdetectorserver.dto.request.DocumentCreateRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateDocumentRequest;
import zev.plagiarismdetectorserver.entity.Document;

import java.util.List;

public interface DocumentService {
    void addDocument(DocumentCreateRequest document);
    void updateDocument(UpdateDocumentRequest request, String documentId);
    void deleteDocument(String documentId);
    Document getDocument(String documentId);
    List<Document> getDocuments(Pageable pageable);
    void addUserReportClassToDocument(String userId, String reportId, String classId, String documentId);
}
