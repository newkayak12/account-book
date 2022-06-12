package com.server.base.repository.categoryRepository;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@ToString
@DynamicUpdate
@DynamicInsert
public class CategorySub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_sub_no")
    private Long categorySubNo;
    @ManyToOne
    @JoinColumn(name = "category_no", referencedColumnName = "category_no")
    private Category category;
    private String category_etc2;
}
