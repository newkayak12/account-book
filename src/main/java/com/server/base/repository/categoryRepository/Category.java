package com.server.base.repository.categoryRepository;

import com.server.base.repository.userRepository.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

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
    @ColumnDefault(value = "-1")
    @Column(name = "user_no")
    private Long userNo;
    private String category_etc1;
    @OneToMany(mappedBy = "category", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CategorySub> category_etc2_list;


    public void setUser(User user){
        this.userNo = user.getUserNo();
    }
}
