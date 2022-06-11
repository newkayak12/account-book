package com.server.base.controller;


import com.server.base.common.exception.ServiceException;
import com.server.base.common.responseContainer.Response;
import com.server.base.common.validations.Validations;
import com.server.base.repository.dto.BankListDto;
import com.server.base.repository.dto.UserDto;
import com.server.base.service.BankCodeService;
import com.server.base.service.BankListService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@Tag(name = "/api/bank", description = "은행 관련")
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/bank")
@Slf4j
@RequiredArgsConstructor
public class BankController {
//    전체Service
    private final BankCodeService bankCodeService;
//    사용자Service
    private final BankListService bankListService;

// 전체
    @ApiOperation("전체 은행 목록 가져오기")
    @GetMapping("/fetchList")
    public Response fetchBankList(){
        return new Response(200, "", bankCodeService.fetchBankList());
    }


//    사용자
    @ApiOperation("사용자 은행 목록 가져오기")
    @GetMapping("/user")
    public Response fetchUserBankList(@RequestHeader(name = HttpHeaders.AUTHORIZATION) Object authorizations){
        return new Response(200, "", bankListService.fetchUserBankList((UserDto)authorizations));
    }
    @ApiOperation("사용자 은행 목록 저장/수정")
    @PostMapping("/user/save")
    @Validated(Validations.saveBankList.class)
    public Response saveBankList(@RequestHeader(name = HttpHeaders.AUTHORIZATION) Object authorizations,
                                 @Valid @RequestBody BankListDto bankListDto){
        UserDto userDto = (UserDto)authorizations;
        bankListDto.setUserId(userDto.getUserId());
        bankListDto.setUserNo(userDto.getUserNo());
        bankListService.saveBankList(bankListDto);
        return new Response(200, "등록에 성공했습니다.", null);
    }
    @ApiOperation("사용자 은행 목록 삭제")
    @DeleteMapping("user/delete/{bankListNo}")
    public Response deleteBankList(@RequestHeader(name = HttpHeaders.AUTHORIZATION) Object authorizations
            ,@PathVariable(name = "bankListNo", required = true)Long bankListNo ) throws ServiceException {
        BankListDto bankListDto = BankListDto.builder().bankListNo(bankListNo)
                .userNo(((UserDto)authorizations).getUserNo()).build();
        bankListService.deleteBankList(bankListDto);
        return new Response(200, "삭제에 성공했습니다.", null);
    }

}
