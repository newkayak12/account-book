package com.server.base.controller;

import com.server.base.common.authorizations.annotations.Authorization;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.responseContainer.Response;
import com.server.base.repository.dto.CategoryDto;
import com.server.base.repository.dto.UserDto;
import com.server.base.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/category")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @ApiOperation(value="카테고리 등록/수정")
    @PostMapping(value = "/save")
    @Authorization
    public Response  saveCategory(@RequestHeader(name = "Authorization") Object userDto, @RequestBody CategoryDto categoryDto){
        categoryDto.setUser((UserDto) userDto);
        categoryService.saveCategory(categoryDto);
        return new Response(200, "저장되었습니다.",null);
    }
    @ApiOperation(value = "카테고리 삭제/숨기기", notes = "1. 기본 카테고리라면 숨기기만 제공 2. 기본 카테고리라면 숨김 상태에서 보임 상태 토글" +
            " 3. 커스텀 카테고리의 경우 완전 삭제")
    @DeleteMapping(value = "/remove")
    @Authorization
    public Response removeCategory(@RequestHeader(name = "Authorization") Object userDto,
                                   @ModelAttribute CategoryDto categoryDto) throws ServiceException {
//        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setUser((UserDto) userDto);
//        categoryDto.setCategoryNo(categoryNo);
        categoryService.removeCategory(categoryDto);
        return new Response(200, "삭제되었습니다.", null);
    }
    @ApiOperation(value = "카테고리 가져오기")
    @GetMapping(value = "/fetch")
    @Authorization
    public Response fetchCategories(@RequestHeader(name = "Authorization") Object userDto){
        return new Response(200, "", categoryService.fetchCategories((UserDto) userDto));
    }

}
