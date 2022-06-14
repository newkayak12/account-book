package com.server.base.repository.categorySubRepository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.base.repository.categoryRepository.Category;
import com.server.base.repository.userRepository.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
public class CategorySub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_sub_no")
    private Long categorySubNo;
    @ManyToOne
    @JoinColumn(name = "user_no", referencedColumnName = "user_no")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_no", referencedColumnName = "category_no")
    private Category category;
    private String category_etc2;

    public void setMainCategory(Category category){
        if(!this.category.equals(category)){
            this.category = category;
        }
    }
    public void setUser(User user){
        if(Objects.isNull(this.user)){
            this.user = user;
        }
    }
}
