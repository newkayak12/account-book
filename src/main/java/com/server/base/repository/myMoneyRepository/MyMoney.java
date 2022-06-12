package com.server.base.repository.myMoneyRepository;

import com.server.base.repository.categoryRepository.Category;
import com.server.base.repository.userRepository.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
public class MyMoney {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_money_no")
    private Long myMoneyNo;
    @ManyToOne
    @JoinColumn(name = "user_no", referencedColumnName = "user_no")
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_no", referencedColumnName = "category_no")
    private Category category;
    private String totalprice;
    @Column(name = "date", updatable = false)
    private LocalDate date;

    @PostPersist
    public void setDate(){
        this.date = LocalDate.now().atStartOfDay().toLocalDate();
    }


    @Builder
    public MyMoney(String totalprice) {
        this.totalprice = totalprice;
    }
    public void setUser(User user){
        this.user = user;
    }
}
