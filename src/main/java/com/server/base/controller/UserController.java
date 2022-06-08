package com.server.base.controller;

import com.server.base.common.constants.Constants;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.responseContainer.EncryptResponse;
import com.server.base.common.responseContainer.Response;
import com.server.base.common.validations.BindingErrorChecker;
import com.server.base.common.validations.Validations;
import com.server.base.repository.dto.UserDto;
import com.server.base.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


//@Tag(name = "/api/user", description = "회원")
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BindingErrorChecker bindingErrorChecker;

    @ApiOperation(value = "로그인", httpMethod = "GET")
    @Parameters({
            @Parameter(name = "userId", required = true, schema = @Schema(type = "String")),
            @Parameter(name = "password", required = true, schema = @Schema(type = "String"))
    })
    @GetMapping(value = "/signIn")
    @Validated(Validations.FirstSign.class)
    public Response signIn(@Valid @ModelAttribute UserDto userDto,
                           HttpServletResponse response) throws ServiceException {
        UserDto result = userService.signIn(userDto);
        response.addHeader(Constants.REFRESH_TOKEN, result.getAuthEntity().getRefreshToken());

        return new EncryptResponse( response, result,null);
    };


    @ApiOperation(value = "간편 로그인", httpMethod = "GET")
    @Parameters({
        @Parameter(name = "passwordSub", required = true, schema = @Schema(type = "String"))
    })
    @GetMapping(value = "easySignIn")
    @Validated(Validations.SecondSign.class)
    public Response easySignIn(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorizations,
            @Valid @ModelAttribute UserDto userDto) throws ServiceException {
        UserDto params = (UserDto) authorizations;
        params.setPasswordSub(userDto.getPasswordSub());
        userService.easySignIn(params);
        return new Response(200, "", null);
    }

    @ApiOperation("회원가입")
    @Parameters({
            @Parameter(name = "userId", required = true, schema = @Schema(type = "String")),
            @Parameter(name = "userName", required = true, schema = @Schema(type = "String")),
            @Parameter(name = "password", required = true, schema = @Schema(type = "String")),
    })
    @PostMapping(value = "/signUp")

    public Response signUp( @Validated(Validations.SignUp.class) @Valid @RequestBody
                        UserDto userDto, HttpServletResponse response) throws ServiceException {
        UserDto result = userService.saveUser(userDto);
        System.out.println("RESULT {}??"+ result);
        response.addHeader(Constants.REFRESH_TOKEN, result.getAuthEntity().getRefreshToken());
        return new EncryptResponse(response, result, null);
    }
    @ApiOperation("로그아웃")
    @GetMapping("/signOut")
    public void singOut(HttpServletResponse response){
        response.addHeader(Constants.REFRESH_TOKEN, null);
        response.addHeader(HttpHeaders.AUTHORIZATION, null);
    }


}
