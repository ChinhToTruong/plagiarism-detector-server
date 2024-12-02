package zev.plagiarismdetectorserver.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import zev.plagiarismdetectorserver.dto.request.DocumentCreateRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateDocumentRequest;
import zev.plagiarismdetectorserver.dto.response.ResponseData;
import zev.plagiarismdetectorserver.entity.Document;
import zev.plagiarismdetectorserver.service.DocumentService;

import java.util.List;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
@Tag(name = "Document Controller")
@Slf4j(topic = "Document")
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/add-document")
    public ResponseData<?> addDocument(DocumentCreateRequest request) {
        log.info("Add document {}", request);
        documentService.addDocument(request);
        return new ResponseData<>("Add document successful");
    }

    @PatchMapping("/update-document/{documentId}")
    public ResponseData<?> updateDocument(@RequestBody @Valid UpdateDocumentRequest updateDocumentRequest, @PathVariable("documentId") String documentId) {
        log.info("Update document {}", updateDocumentRequest);
        documentService.updateDocument(updateDocumentRequest, documentId);
        return new ResponseData<>("Update document successful");
    }

    @GetMapping
    public ResponseData<?> getDocuments(Pageable pageable) {
        List<Document> documents = documentService.getDocuments(pageable);
        return new ResponseData<>("Get documents successful", documents);
    }

    @DeleteMapping("/delete-document")
    public ResponseData<?> deleteDocumentById(@RequestParam("documentId") String documentId) {
        log.info("Delete document {}", documentId);
        documentService.deleteDocument(documentId);
        return new ResponseData<>("Delete document successful");
    }

    @GetMapping("/{documentId}")
    public ResponseData<?> getDocumentById(@PathVariable("documentId") String documentId) {
        log.info("Get document {}", documentId);
        Document document = documentService.getDocument(documentId);
        return new ResponseData<>("Get document successful", document);
    }

    @GetMapping("/reference-document/{documentId}")
    public ResponseData<?> referenceDocument(@PathVariable("documentId") String documentId, @RequestParam("userId") String userId, @RequestParam("reportId") String reportId, @RequestParam("classId") String classId) {
        log.info("Reference document {}", userId);
        documentService.addUserReportClassToDocument(userId, reportId, classId, documentId);
        return new ResponseData<>("Reference document successful");
    }
}
