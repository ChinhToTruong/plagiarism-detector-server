package zev.plagiarismdetectorserver.service;

import org.springframework.web.multipart.MultipartFile;
import zev.plagiarismdetectorserver.dto.request.AIDetectRequest;
import zev.plagiarismdetectorserver.dto.response.AIDetectReportResponse;


public interface PlagiarismService {

  AIDetectReportResponse submitCopyCheckReport(AIDetectRequest text, MultipartFile file);

  AIDetectReportResponse getCopyCheckReportById(String id);
}
