package zev.plagiarismdetectorserver.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zev.plagiarismdetectorserver.dto.request.AIDetectRequest;
import zev.plagiarismdetectorserver.dto.response.ResponseData;
import zev.plagiarismdetectorserver.service.PlagiarismService;


@RestController
@RequestMapping("/plagiarism")
@RequiredArgsConstructor
@Tag(name = "Plagiarism Controller")
@Slf4j(topic = "Plagiarism")
public class PlagiarismController {

  private final PlagiarismService plagiarismService;

  @PostMapping(value = "/add-document")
  public ResponseData<?> detectDocument(
      @RequestBody @Valid AIDetectRequest request) {

    log.info("Detect document");

    String id = plagiarismService.submitCopyCheckReport(request).getData().get("id")
        .toString();

    return new ResponseData<>("detected", id);
  }

  @GetMapping("/{documentId}")
  public ResponseData<?> getDocument(
      @PathVariable(name = "documentId") String documentId) {
    log.info("Get document");

    var data = plagiarismService.getCopyCheckReportById(documentId);

    return new ResponseData<>("get document successful", data);
  }
}
