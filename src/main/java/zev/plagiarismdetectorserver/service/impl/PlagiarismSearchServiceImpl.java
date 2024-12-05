package zev.plagiarismdetectorserver.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import zev.plagiarismdetectorserver.dto.request.AIDetectRequest;
import zev.plagiarismdetectorserver.dto.response.AIDetectReportResponse;
import zev.plagiarismdetectorserver.repository.clients.PlagiarismSearchClient;
import zev.plagiarismdetectorserver.service.PlagiarismService;


@Service
@RequiredArgsConstructor
@Slf4j
public class PlagiarismSearchServiceImpl implements PlagiarismService {

  private final PlagiarismSearchClient plagiarismSearchClient;

  @Value("${plagiarism-search.token}")
  private String token;

  @Transactional
  @Override
  public AIDetectReportResponse submitAiCheckReport(AIDetectRequest text, MultipartFile file) {
    log.info("Submitting AiCheck Report to Plagiarism");

    if (text == null && file == null) {
      // neu k ton tai ca 2 thi throw loi
      return null;
    }

    if (text != null && file != null) {
      // neu ton tai ca 2 file va text thi yeu cau chon 1 trong 2
      return null;
    }

    if (file != null) {
      return plagiarismSearchClient.submitAiReport(token, file);
    }

    return plagiarismSearchClient.submitAiReport(token, text);

  }

  @Override
  public AIDetectReportResponse getAiCheckReportById(String id) {
    log.info("Getting AiCheck Report by ID");

    return plagiarismSearchClient.getAiReportById(token, id);
  }

  @Transactional
  @Override
  public AIDetectReportResponse submitCopyCheckReport(AIDetectRequest text, MultipartFile file) {
    log.info("Submitting Copy Check Report to Plagiarism");

    if (text == null && file == null) {
      // neu k ton tai ca 2 thi throw loi
      return null;
    }

    if (text != null && file != null) {
      // neu ton tai ca 2 file va text thi yeu cau chon 1 trong 2
      return null;
    }

    if (file != null) {
      return plagiarismSearchClient.submitCopyCheckReport(token, file);
    }

    return plagiarismSearchClient.submitCopyCheckReport(token, text);
  }

  @Override
  public AIDetectReportResponse getCopyCheckReportById(String id) {
    log.info("Getting Copy Check Report by ID");
    return plagiarismSearchClient.getCopyCheckReportById(token, id);
  }


}
