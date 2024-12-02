package zev.plagiarismdetectorserver.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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

    @Transactional(rollbackOn = Exception.class)
    @Override
    public AIDetectReportResponse submitAiCheckReport(AIDetectRequest text, MultipartFile file) {

        if (text == null && file == null) {
            // neu k ton tai ca 2 thi throw loi
            return null;
        }
        if (text != null && file != null) {
            // neu ton tai ca 2 file va text thi yeu cau chon 1 trong 2
            return null;
        }
        if (file != null) {
            return plagiarismSearchClient.submitAiReport(token,file);
        }
        return plagiarismSearchClient.submitAiReport(token, text);

    }

    @Override
    public AIDetectReportResponse getAiCheckReportById(String id) {
        return plagiarismSearchClient.getAiReportById(token, id);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public AIDetectReportResponse submitCopyCheckReport(AIDetectRequest text, MultipartFile file) {
        if (text == null && file == null) {
            // neu k ton tai ca 2 thi throw loi
            return null;
        }
        if (text != null && file != null) {
            // neu ton tai ca 2 file va text thi yeu cau chon 1 trong 2
            return null;
        }
        if (file != null) {
            return plagiarismSearchClient.submitCopyCheckReport(token,file);
        }
        return plagiarismSearchClient.submitCopyCheckReport(token, text);
    }

    @Override
    public AIDetectReportResponse getCopyCheckReportById(String id) {
        return plagiarismSearchClient.getCopyCheckReportById(token, id);
    }


}
