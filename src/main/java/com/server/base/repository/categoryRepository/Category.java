package com.server.base.repository.categoryRepository;

import com.server.base.repository.userRepository.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

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
@EntityListeners(AuditingEntityListener.class)
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
    private Boolean cateFlag;

    @Column(name = "cate_is_basic")
    private  Boolean cateIsBasic;

    @Column(name = "cate_image", columnDefinition = "TEXT")
    private String cateImage;

    @Column(name = "cate_is_outcome")
    private Boolean isOutcome;




    public void setUser(User user){
        this.user = user;
    }
    public void changeCategoryName(String categoryName){
        if(this.cateName!= categoryName){
            this.cateName= categoryName;
        }
    }
    public void changeImage(String image64){
        if(this.cateImage != image64){
            this.cateImage = image64;
        }
    }
    public void hideCategory(){
        this.cateFlag=!this.cateFlag;
    }

    @PrePersist
    private void setting(){
        if(Objects.isNull(this.cateFlag)){
            this.cateFlag = true;
        }
        if(Objects.isNull(this.cateIsBasic)){
            this.cateIsBasic=false;
        }
    }
}
