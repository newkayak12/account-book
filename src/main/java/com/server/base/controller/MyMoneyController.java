package com.server.base.controller;

import com.server.base.common.authorizations.annotations.Authorization;
import com.server.base.common.dto.PagingDto;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.responseContainer.Response;
import com.server.base.common.validations.Validations;
import com.server.base.repository.dto.MyMoneyDto;
import com.server.base.repository.dto.UserDto;
import com.server.base.service.MyMoneyService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/mymoney")
@Slf4j
@RequiredArgsConstructor
public class MyMoneyController {
    private MyMoneyService myMoneyService;

    @ApiOperation(value = "예산 등록/수정")
    @PostMapping(value = "/save")
    @Authorization
    public Response saveMyMoney(@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorizations,
            @Validated(Validations.SaveMyMoney.class) @Valid @RequestBody MyMoneyDto myMoneyDto){
            myMoneyDto.setUser((UserDto) authorizations);
            myMoneyService.saveMyMoney(myMoneyDto);
        return new Response(200, "등록되었습니다.", null);
    }
    @ApiOperation(value = "예산 삭제")
    @DeleteMapping(value = "/remove")
    @Authorization
    public  Response removeMyMoney(@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorizations,
                                   @Validated(Validations.RemoveMyMoney.class) @Valid @RequestBody MyMoneyDto myMoneyDto) throws ServiceException {
        myMoneyDto.setUser((UserDto) authorizations);
        myMoneyService.removeMyMoney(myMoneyDto);
        return new Response(200, "삭제되었습니다.", null);
    }
    @ApiOperation(value = "예산 가져오기")
    @GetMapping(value = "/fetch")
    @Authorization
    public Response fetchMyMoney(@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorization,
                                 @Validated({Validations.RegDate.class}) @Valid @ModelAttribute PagingDto pagingDto){
        return new Response(200, "", myMoneyService.fetchMyMoney((UserDto) authorization, pagingDto));
    }
}

