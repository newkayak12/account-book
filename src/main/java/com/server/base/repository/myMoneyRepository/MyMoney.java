package com.server.base.repository.myMoneyRepository;

import com.server.base.common.converter.IsMainConverter;
import com.server.base.common.enums.IsMain;
import com.server.base.repository.categoryRepository.Category;
import com.server.base.repository.userRepository.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

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
@EntityListeners(AuditingEntityListener.class)
public class MyMoney {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_money_no")
    private Long myMoneyNo;
    @ManyToOne
    @JoinColumn(name = "user_no", referencedColumnName = "user_no")
    private User user;

    private String totalprice;
    @Column(name = "date", updatable = false)
    private LocalDate date;
    @Convert(converter = IsMainConverter.class)
    private IsMain isMain;

    @ManyToOne
    @JoinColumn(name = "category_no", referencedColumnName = "category_no")
    private Category category;

    @PostPersist
    public void setDate(){
        this.date = LocalDate.now().atStartOfDay().toLocalDate();
    }

//
//    public void setCategory(Category category) {
//        this.category = category;
//    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public void setUser(User user){
        this.user = user;
    }
}
