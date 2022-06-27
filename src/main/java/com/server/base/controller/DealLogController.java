package com.server.base.controller;

import com.server.base.common.authorizations.annotations.Authorization;
import com.server.base.common.dto.PagingDto;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.responseContainer.Response;
import com.server.base.common.validations.Validations;
import com.server.base.repository.dto.DealLogDto;
import com.server.base.repository.dto.UserDto;
import com.server.base.service.DealLogService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/dealLog")
@Slf4j
@RequiredArgsConstructor
public class DealLogController {
    private final DealLogService dealLogService;

    @ApiOperation(value = "가계부 등록/수정")
    @PostMapping(value = "/save")
    @Authorization
    public Response saveDealLog(@RequestHeader(name = "Authorization") Object userDto,
                                @RequestBody DealLogDto dealLogDto){
        dealLogDto.setUser((UserDto) userDto);
        dealLogService.saveDealLog(dealLogDto);
        return new Response(200, "저장되었습니다.",null);
    }
    @ApiOperation(value = "가계부 삭제")
    @DeleteMapping(value = "/remove")
    @Authorization
    public Response removeDealLog(@RequestHeader(name = "Authorization") Object userDto,
                                  @ModelAttribute DealLogDto dealLogDto){
        dealLogDto.setUser((UserDto) userDto);
        dealLogService.removeDealLog(dealLogDto);
        return new Response(200, "삭제되었습니다.", null);
    }
    @ApiOperation(value = "가계부 단건")
    @GetMapping(value = "/fetch")
    @Authorization
    public Response fetchDealLog(@RequestHeader(name = "Authorization") Object userDto,
                                 @ModelAttribute DealLogDto dealLogDto) throws ServiceException {
        dealLogDto.setUser((UserDto) userDto);
        return new Response(200, "", dealLogService.fetchDealLog(dealLogDto));
    }

    @ApiOperation(value = "가계부 리스트로 가져오기")
    @GetMapping(value = "/fetchList")
    @Authorization
    public Response fetchDealLogList(@RequestHeader(name = "Authorization") Object userDto,
                  @Validated(Validations.Type.class) @Valid @ModelAttribute PagingDto pagingDto) throws ServiceException {
        return new Response(200, "", dealLogService.fetchDealLogList((UserDto)userDto, pagingDto));
    }

    @ApiOperation(value = "통계")
    @GetMapping(value = "/statistics")
    @Authorization
    public Response fetchStatistics(@RequestHeader(name = "Authorization") Object userDto,
                                    @ModelAttribute PagingDto pagingDto) throws ServiceException {
        return new Response(200, "", dealLogService.fetchStatistics((UserDto)userDto, pagingDto));
    }


}
