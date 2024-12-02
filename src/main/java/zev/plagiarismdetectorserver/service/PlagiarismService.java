package zev.plagiarismdetectorserver.service;

import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;
import zev.plagiarismdetectorserver.dto.request.AIDetectRequest;
import zev.plagiarismdetectorserver.dto.response.AIDetectReportResponse;


public interface PlagiarismService {
    AIDetectReportResponse submitAiCheckReport(AIDetectRequest text, MultipartFile file);
    AIDetectReportResponse getAiCheckReportById(String id);
    AIDetectReportResponse submitCopyCheckReport(AIDetectRequest text, MultipartFile file);
    AIDetectReportResponse getCopyCheckReportById(String id);
}
