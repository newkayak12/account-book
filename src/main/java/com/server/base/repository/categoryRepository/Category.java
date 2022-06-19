package com.server.base.repository.categoryRepository;

import com.server.base.repository.userRepository.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

//@Entity
//@Table
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
    @Column(name = "cate_idx", nullable = false)
    private Long categoryNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_idx", nullable = false)
    private User user;

    @Column(name = "cate_name", length = 50)
    private String cateName;

    @Column(name = "cate_flag", nullable = false)
    private Boolean cateFlag = false;

    @Column(name = "cate_image", length = 200)
    private String cateImage;


    public void setUser(User user){
        this.user = user;
    }
}
