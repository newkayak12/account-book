package com.server.base.common.validations;

import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

public class BindingErrorChecker {
    public void checkError(BindingResult bindingResult) throws ServiceException{
        if(bindingResult.hasErrors()){
            List<ObjectError> errorList  = bindingResult.getAllErrors();
            String invalidParams = errorList.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
            throw new ServiceException(Exceptions.BINDING_ERROR,invalidParams.toString());
        }
    }
}
