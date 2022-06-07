package com.server.base.repository.categoryRepository;

import com.server.base.repository.userRepository.User;
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
    @Builder
    public Category(String category_etc1, String category_etc2, Boolean categoryIsDefault) {
        this.category_etc1 = category_etc1;
        this.category_etc2 = category_etc2;
        this.categoryIsDefault = categoryIsDefault;
    }
    public void setUser(User user){
        this.userNo = user.getUserNo();
    }
}
