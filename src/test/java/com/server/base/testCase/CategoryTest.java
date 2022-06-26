package com.server.base.testCase;

import com.server.base.baseTest.BaseTest;
import com.server.base.repository.dto.CategoryDto;
import com.server.base.repository.dto.UserDto;
import lombok.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest extends BaseTest {


    @BeforeEach
    void settings(){
        super.prefix += "/category";
    }

    @Test
    void fetchTest () {
        this.get("/fetch", null);
    }

    @Test
    void saveNewOnTest(){
        String img = "data:image/png;base64,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";
        CategoryDto dto  = CategoryDto.builder().cateIsBasic(false).cateImage(img).cateFlag(true).cateName("APPLE").build();
        this.post("/save",dto );
    }
    @Test
    void modifyCategoryTest(){
        CategoryDto dto = CategoryDto.builder().categoryNo(4L).cateName("외식비").build();
        this.post("/save", dto);
    }
    @Test
    void hideCategory(){
        CategoryDto dto = CategoryDto.builder().categoryNo(4L).user(new UserDto()).cateName("외식비").build();
        this.delete("/remove", Users.builder().categoryNo(4L).build());
    }



}
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
 class Users{
    public Long categoryNo;
}