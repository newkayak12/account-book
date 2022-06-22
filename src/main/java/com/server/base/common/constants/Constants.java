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
    public static Long LOCK_DOWN_TIME;
    public static Integer SUB_CATEGORY_MAX_COUNT;

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
        CORS = CORS;
    }
    @Value("${CONSTANTS.LOCKDOWNTIME}")
    public void setLockDownTime(Long _lock_down_time){LOCK_DOWN_TIME=_lock_down_time; }
    @Value("${CONSTANTS.SUBCATEGORYMAXCOUNT}")
    public void setSubCategoryMaxCount(Integer _sub_category_max_count){SUB_CATEGORY_MAX_COUNT = _sub_category_max_count; }


    public static String TEST_ACCESS_TOKEN;
    public static String TEST_REFRESH_TOKEN;
    public static String TEST_PREFIX;

    @Value("${TEST.ACCESS_TOKEN}")
    public void setTestAccessToken(String _test_access_token){
        TEST_ACCESS_TOKEN = _test_access_token;
    }
    @Value("${TEST.REFRESH_TOKEN}")
    public void setTestRefreshToken(String _test_refresh_token){
        TEST_REFRESH_TOKEN = _test_refresh_token;
    }
    @Value("${TEST.PREFIX}")
    public void setTestPrefix(String _test_prefix){
        TEST_PREFIX = _test_prefix;
    }
}
