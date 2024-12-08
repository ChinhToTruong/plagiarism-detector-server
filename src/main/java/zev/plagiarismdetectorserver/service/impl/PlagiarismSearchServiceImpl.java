package zev.plagiarismdetectorserver.service.impl;

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

  @Value("${plagiarism-search.token}")
  private String token;


  @Transactional(rollbackFor = Exception.class)
  @Override
  public AIDetectReportResponse submitCopyCheckReport(AIDetectRequest text, MultipartFile file) {
    log.info("Submitting Copy Check Report to Plagiarism");

    if (text == null && file == null) {
      // neu k ton tai ca 2 thi throw loi
      throw new DocumentEmpty();
    } else if (text != null && file == null) {
      
      return plagiarismSearchClient.submitCopyCheckReport(token, text);
    } else {

      return plagiarismSearchClient.submitCopyCheckReport(token, file);
    }
  }

  @Override
  public AIDetectReportResponse getCopyCheckReportById(String id) {
    log.info("Getting Copy Check Report by ID");
    return plagiarismSearchClient.getCopyCheckReportById(token, id);
  }


}
