package zev.plagiarismdetectorserver.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import zev.plagiarismdetectorserver.dto.request.AIDetectRequest;
import zev.plagiarismdetectorserver.dto.response.AIDetectReportResponse;
import zev.plagiarismdetectorserver.exception.DocumentEmpty;
import zev.plagiarismdetectorserver.repository.clients.PlagiarismSearchClient;
import zev.plagiarismdetectorserver.service.PlagiarismService;


@Service
@RequiredArgsConstructor
@Slf4j
public class PlagiarismSearchServiceImpl implements PlagiarismService {

  private final PlagiarismSearchClient plagiarismSearchClient;

  @Value("Bearer ${plagiarism-search.token}")
  private String token;


  @Transactional(rollbackFor = Exception.class)
  @Override
  public AIDetectReportResponse submitCopyCheckReport(AIDetectRequest request) {
    log.info("Submitting Copy Check Report to Plagiarism");
    
    return plagiarismSearchClient.submitCopyCheckReport(token, request);

  }

  @Override
  public AIDetectReportResponse getCopyCheckReportById(String id) {
    log.info("Getting Copy Check Report by ID");
    return plagiarismSearchClient.getCopyCheckReportById(token, id);
  }

  private String getTextFromFile(MultipartFile file) {
    StringBuilder contentBuilder = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      String line;
      while ((line = reader.readLine()) != null) {
        contentBuilder.append(line).append("\n");
      }
    } catch (IOException e) {
      return "Error reading file: " + e.getMessage();
    }
    return contentBuilder.toString();
  }
}
