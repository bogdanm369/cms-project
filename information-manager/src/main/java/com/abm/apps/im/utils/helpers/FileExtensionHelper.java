package com.abm.apps.im.utils.helpers;

import com.abm.apps.evcommons.beans.domain.http.contentEnums.DataFormat;
import com.abm.apps.im.utils.InformationExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class FileExtensionHelper {

  @Autowired private InformationExceptionHelper exceptionHelper;

  private Map<String, DataFormat> fileExtensions = new HashMap<>();

  @PostConstruct
  private void init() {
    fileExtensions.put("TXT", DataFormat.TEXT);
    fileExtensions.put("JPEG", DataFormat.BINARY);
    fileExtensions.put("JPG", DataFormat.BINARY);
  }

  public DataFormat getDataFormatForExtension(String fileExtension) {

    if (StringUtils.isEmpty(fileExtension)) {
      exceptionHelper.throwValidationException("fileExtension argument should contain a value");
    }

    if (fileExtension.startsWith(".")) {
      fileExtension = fileExtension.substring(0 + 1, fileExtension.length());
    }

    return fileExtensions.get(fileExtension);
  }
}
