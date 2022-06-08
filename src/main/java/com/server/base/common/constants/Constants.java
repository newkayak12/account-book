package com.server.base.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Constants {

    public static String PROJECT_NAME;
    public static Boolean IS_DEV_MODE;
    public static String SALT_VALUE;
    public static String ACCESS_TOKEN;
    public static String REFRESH_SALT_VALUE;
    public static String REFRESH_TOKEN;
    public static String CORS;
    public static List<String> API_PATH;

    @Value("${CONSTANTS.PROJECTNAME}")
    public void setProjectName(String _projectName){
        PROJECT_NAME=_projectName;
    }
    @Value("${CONSTANTS.APIPATHS}")
    public void setApiPath(String _apiPath){ API_PATH = Arrays.asList(_apiPath.split(",")); }
    @Value("${CONSTANTS.SALTVALUE}")
    public void setSaltValue(String _saltValue) { SALT_VALUE = _saltValue; }
    @Value("${CONSTANTS.REFRESHSALTVALUE}")
    public void setRefreshSaltValue(String _refresh_salt_value){REFRESH_SALT_VALUE=_refresh_salt_value; }
    @Value("${CONSTANTS.ISDEVMODE}")
    public void setIsDevMode(Boolean _is_dev_mode){ IS_DEV_MODE = _is_dev_mode; }
    @Value("${CONSTANTS.ACCESSTOKENNAME}")
    public void setAccessToken(String _access_token){ACCESS_TOKEN=_access_token; }
    @Value("${CONSTANTS.REFRESHTOKENNAME}")
    public void setRefreshToken(String _refresh_token){REFRESH_TOKEN=_refresh_token;}
    @Value("${CONSTANTS.CORS}")
    public void setCORS(String CORS) {
        Constants.CORS = CORS;
    }
}
