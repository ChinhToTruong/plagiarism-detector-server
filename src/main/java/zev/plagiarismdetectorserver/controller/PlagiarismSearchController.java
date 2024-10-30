package zev.plagiarismdetectorserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zev.plagiarismdetectorserver.dto.request.AIDetectRequest;
import zev.plagiarismdetectorserver.dto.response.AIDetectReportResponse;
import zev.plagiarismdetectorserver.service.PlagiarismService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/detect")
public class PlagiarismSearchController {
    private final PlagiarismService plagiarismService;
    @PostMapping("/ai/text")
    public Object submitAiCheckReport(@RequestBody AIDetectRequest text, MultipartFile file) {
        return plagiarismService.submitAiCheckReport(text, file);
    }

    @GetMapping("/ai/{id}")
    public AIDetectReportResponse getAiCheckReportById(@PathVariable String id) {
        return plagiarismService.getAiCheckReportById(id);
    }

    @PostMapping("/copy/text")
    public Object submitCopyCheckReport(@RequestBody AIDetectRequest text, MultipartFile file) {
        return plagiarismService.submitAiCheckReport(text, file);
    }

    @GetMapping("/copy/{id}")
    public AIDetectReportResponse getCopyCheckReportById(@PathVariable String id) {
        return plagiarismService.getAiCheckReportById(id);
    }

}
