package com.server.base.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PagingContainer <T> {
    List<T> list;
    PageInfo pageInfo;
    public  PagingContainer (Pageable pageInfo, Page<T> listFunction){
        this.list = listFunction.getContent();
        this.pageInfo = PageInfo.builder()
                .page(pageInfo.getOffset())
                .pageNumber(pageInfo.getPageNumber())
                .pageSize(pageInfo.getPageSize())
//                .offset(pageInfo.getOffset())
                .hasNext(listFunction.hasNext())
                .hasPrevious(pageInfo.hasPrevious())
                .totalElement(listFunction.getTotalElements())
                .build();
    }

}
@Setter
@AllArgsConstructor
@Builder
class PageInfo{
    private Long page;
    private Integer pageNumber;
    private Integer pageSize;
    //        private Long offset;
    private Boolean hasNext;
    private Boolean hasPrevious;
    private Long totalElement;


}