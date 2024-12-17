package zev.plagiarismdetectorserver.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import zev.plagiarismdetectorserver.dto.request.AIDetectRequest;
import zev.plagiarismdetectorserver.dto.response.AIDetectReportResponse;
import zev.plagiarismdetectorserver.entity.Document;
import zev.plagiarismdetectorserver.exception.DocumentEmpty;
import zev.plagiarismdetectorserver.repository.DocumentRepository;
import zev.plagiarismdetectorserver.repository.clients.PlagiarismSearchClient;
import zev.plagiarismdetectorserver.service.PlagiarismService;


@Service
@RequiredArgsConstructor
@Slf4j
public class PlagiarismSearchServiceImpl implements PlagiarismService {

  private final PlagiarismSearchClient plagiarismSearchClient;
  private final DocumentRepository documentRepository;

  @Value("Bearer ${plagiarism-search.token}")
  private String token;


  @Transactional(rollbackFor = Exception.class)
  @Override
  public AIDetectReportResponse submitCopyCheckReport(AIDetectRequest request) {
    log.info("Submitting Copy Check Report to Plagiarism");
    /* check url, text or file is not plagiarism?
     * if plagiarism -> return report id
     * if not plagiarism -> plagiarism new document
     */

    AIDetectReportResponse response = new AIDetectReportResponse();

    // check report title is existed (url, text or file is save as title report)
    if (request.getUrl() == null || request.getTitle() == null || request.getFile() == null) {

      throw new DocumentEmpty();
    }
    // get document
    Optional<Document> finByUrl = documentRepository.findByTitle(request.getUrl());
    Optional<Document> finByFile = documentRepository.findByTitle(request.getFile().getName());
    Optional<Document> finByText = documentRepository.findByTitle(request.getText());

    // if found -> return report id
    if (finByUrl.isPresent()) {

      response = getCopyCheckReportById(finByUrl.get().getReportId());
    }

    // if not found -> do plagiarism and save document to storage
    response = plagiarismSearchClient.submitCopyCheckReport(token, request);

    var newDocument = Document.builder()
        .title(response.getData().get("title").toString())
        .reportId(response.getData().get("id").toString())
        .build();
    documentRepository.save(newDocument);

    return response;
  }

  @Override
  public AIDetectReportResponse getCopyCheckReportById(String id) {
    log.info("Getting Copy Check Report by ID");
    return plagiarismSearchClient.getCopyCheckReportById(token, id);
  }

}
