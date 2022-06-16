package com.server.base.controller;

import com.server.base.common.authorizations.annotations.Authorization;
import com.server.base.common.dto.PagingDto;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.responseContainer.Response;
import com.server.base.repository.dto.MainAccountDto;
import com.server.base.repository.dto.UserDto;
import com.server.base.service.MainAccountService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/mainAccount")
@Slf4j
@RequiredArgsConstructor
public class MainAccountController {
    private final MainAccountService mainAccountService;

    @ApiOperation(value = "가계부 등록/수정")
    @PostMapping(value = "/save")
    @Authorization
    public Response saveAccount(@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorizations,
                                @RequestBody MainAccountDto mainAccountDto) {
        mainAccountDto.setUserNo(((UserDto) authorizations).getUserNo());
        mainAccountDto.setUserId(((UserDto) authorizations).getUserId());
        mainAccountService.saveAccount(mainAccountDto);
        return new Response(200, "저장되었습니다.", null);
    }

    @ApiOperation(value = "가계부 삭제")
    @DeleteMapping(value = "/remove")
    public Response removeAccount(/*@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorizations,*/
                                  @ModelAttribute MainAccountDto mainAccountDto) throws ServiceException {
        mainAccountService.removeAccount(mainAccountDto);
        return new Response(200, "삭제되었습니다.", null);
    }

    @ApiOperation(value = "가계부 전체 가져오기 Month:1/ Week:2/ Day:3")
    @GetMapping(value = "/fetch")
    @Authorization
    public Response fetchAccount(@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorizations, @ModelAttribute PagingDto pagingDto){
        return new Response(200, "", mainAccountService.fetchAccount((UserDto)authorizations, pagingDto));
    }

    @ApiOperation(value = "가계부 지출/수입 전체 가져오기")
    @GetMapping(value = "/inAndOut")
    @Authorization
    public Response fetchInAndOut(@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorizations, @ModelAttribute PagingDto pagingDto){
        return new Response(200, "", mainAccountService.fetchInAndOut((UserDto) authorizations, pagingDto));
    }

    @ApiOperation(value = "전체 지출/수입 ")
    @GetMapping(value = "/total/inAndOut")
    @Authorization
    public Response fetchTotalInAndOut(@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorizations, @ModelAttribute PagingDto pagingDto){
        return new Response(200, "", mainAccountService.fetchTotalInAndOut((UserDto)authorizations, pagingDto));
    }

}
