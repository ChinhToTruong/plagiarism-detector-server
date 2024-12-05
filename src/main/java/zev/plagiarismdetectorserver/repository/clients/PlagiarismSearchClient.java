package zev.plagiarismdetectorserver.repository.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zev.plagiarismdetectorserver.dto.request.AIDetectRequest;
import zev.plagiarismdetectorserver.dto.response.AIDetectReportResponse;

@FeignClient(
    value = "plagiarism-search",
    url = "${plagiarism-search.host:https://plagiarismsearch.com/api/v3}"
)
public interface PlagiarismSearchClient {

  // Api plagiarism detect with ai-generated method
  @GetMapping(value = "/ai-reports/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  AIDetectReportResponse getAiReportById(@RequestHeader("Authorization") String token,
      @PathVariable String id);

  @PostMapping(value = "/ai-reports/create")
  AIDetectReportResponse submitAiReport(@RequestHeader("Authorization") String token,
      @RequestBody AIDetectRequest text);

  @PostMapping(value = "/ai-reports/create")
  AIDetectReportResponse submitAiReport(@RequestHeader("Authorization") String token,
      MultipartFile file);


  // Api plagiarism detect with copy-resource method
  @GetMapping(value = "/reports/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  AIDetectReportResponse getCopyCheckReportById(@RequestHeader("Authorization") String token,
      @PathVariable String id);

  @PostMapping(value = "/reports/create")
  AIDetectReportResponse submitCopyCheckReport(@RequestHeader("Authorization") String token,
      @RequestBody AIDetectRequest text);

  @PostMapping(value = "/reports/create")
  AIDetectReportResponse submitCopyCheckReport(@RequestHeader("Authorization") String token,
      MultipartFile file);
}
