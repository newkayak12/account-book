package com.server.base.repository.categoryRepository;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@DynamicUpdate
@DynamicInsert
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_no")
    private Long categoryNo;
    @Column(name = "user_no")
    private Long userNo;
    private String category_etc1;
    private String category_etc2;
    @Column(nullable = false)
    private Boolean categoryIsDefault;

}
