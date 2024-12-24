package zev.plagiarismdetectorserver.dto.request;

import java.io.Serializable;
import java.util.List;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter
public class AIDetectRequest implements Serializable {

  private String text;
  private MultipartFile file;
  private String url;
  private String title;
  private String callbackUrl;
  private String remoteId;
  private int isSearchWeb;
  private int isSearchFilterChars;
  private int isSearchFilterReferences;
  private int isSearchFilterQuotes;
  private List<String> searchWebDisableUrls;
  private List<String> searchWebExcludeUrls;
  private int isSearchAi;
  private int isSearchStorage;
  private int isSearchStorageOrganization;
  private int isAddStorage;
  private List<Integer> searchStorageUserGroup;
  private double searchStorageProximity;
  private int searchStorageSensibilityPercentage;
  private int searchStorageSensibilityWords;
  private int storageGroupId;
  private int storageUserId;
  private int storageFileId;
  private Integer limitWords;
  private int isJson;
  private int force;
}
