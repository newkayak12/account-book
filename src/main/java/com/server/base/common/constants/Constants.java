package com.server.base.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static String PROJECT_NAME;

    @Value("${CONSTANTS.PROJECTNAME}")
    public void setProjectName(String _projectName){
        PROJECT_NAME=_projectName;
    }
}
