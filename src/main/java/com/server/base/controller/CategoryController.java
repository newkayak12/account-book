package com.server.base.controller;

import com.server.base.common.authorizations.annotations.Authorization;
import com.server.base.common.dto.PagingDto;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.responseContainer.Response;
import com.server.base.common.validations.Validations;
import com.server.base.repository.dto.CategoryDto;
import com.server.base.repository.dto.CategorySubDto;
import com.server.base.repository.dto.UserDto;
import com.server.base.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/category")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    @ApiOperation(value = "메인 카테고리 등록/수정", httpMethod = "POST")
    @PostMapping(value = "/main")
    @Authorization
    public Response saveMain(@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorizations,
                @Validated(Validations.saveMain.class) @Valid @RequestBody  CategoryDto categoryDto){
        System.out.println(authorizations);
        UserDto userDto = (UserDto) authorizations;
        categoryDto.setUser(userDto);
        categoryService.saveMain(categoryDto);
        return new Response(200, "등록이 완료되었습니다.", null);
    }
    @ApiOperation(value = "메인 카테고리 삭제", httpMethod = "DELETE")
    @DeleteMapping(value = "/main/{categoryNo}")
    @Authorization
    public Response removeMain(@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorizations,
                               @PathVariable Long categoryNo) throws ServiceException {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setUser((UserDto) authorizations);
        categoryDto.setCategoryNo(categoryNo);
        categoryService.removeMain(categoryDto);
        return new Response(1, "삭제했습니다.", null);
    }

    @ApiOperation(value = "서브 카테고리 등록/수정", httpMethod = "POST")
    @PostMapping(value = "/sub")
    @Authorization
    public Response saveSub(@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorizations,
            @Validated(Validations.saveSub.class) @Valid @RequestBody CategorySubDto categorySubDto) throws ServiceException {

        categoryService.saveSub((UserDto) authorizations, categorySubDto);
        return new Response(200, "등록이 완료되었습니다.", null);
    }

    @ApiOperation(value = "서브 카테고리 삭제", httpMethod = "DELETE")
    @DeleteMapping(value = "/sub/{categorySubNo}")
    @Authorization
    public Response removeSub(@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorizations,
                              @PathVariable Long categorySubNo) throws ServiceException {
        CategorySubDto categorySubDto = new CategorySubDto();
        categorySubDto.setUser((UserDto) authorizations);
        categorySubDto.setCategorySubNo(categorySubNo);
        categoryService.removeSub(categorySubDto);
        return new Response(1, "삭제했습니다.", null);
    }

    @ApiOperation(value = "메인 카테고리 가져오기 (페이징)")
    @GetMapping(value = "/mains")
    @Authorization
    public Response fetchMains (@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorizations,
                                @Validated(Validations.paging.class) @Valid @ModelAttribute PagingDto pagingDto){
        return new Response(200, "", categoryService.fetchMains((UserDto) authorizations, pagingDto));
    }

    @ApiOperation(value = "서브 카테고리 가져오기")
    @GetMapping(value = "/subs")
    @Authorization
    public Response fetchSubs(@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object authorizations,
                            @Validated(Validations.fetchSub.class) @Valid  CategoryDto categoryDto) throws ServiceException {
        return new Response(200, "", categoryService.fetchSub((UserDto) authorizations, categoryDto));
    }



}
