package com.osmos.intercom.service.config;


import com.osmos.intercom.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserConfigurationService {

  @Autowired
  ResourceUtils resourceUtils;

  @Value("classpath:/input/intercomUserConfig.json")
  Resource userConfigResource;

  public String getUserConfigurations() {
    return resourceUtils.getResourceData(userConfigResource) ;
  }
}
