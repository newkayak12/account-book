package com.server.base.controller;

import com.server.base.common.authorizations.annotations.Authorization;
import com.server.base.common.responseContainer.Response;
import com.server.base.common.validations.Validations;
import com.server.base.repository.categoryRepository.Category;
import com.server.base.repository.categoryRepository.CategorySub;
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
        UserDto userDto = (UserDto) authorizations;
        categoryDto.setUser(userDto);
        categoryService.saveMain(categoryDto);
        return new Response(200, "등록이 완료되었습니다.", null);
    }

    @ApiOperation(value = "서브 카테고리 등록/수정", httpMethod = "POST")
    @PostMapping(value = "/sub")
    @Authorization
    public Response saveSub(@RequestBody CategorySubDto categorySubDto){

    }




}
