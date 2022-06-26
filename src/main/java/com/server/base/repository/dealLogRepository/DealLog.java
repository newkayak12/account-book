package com.server.base.repository.dealLogRepository;

import com.server.base.repository.categoryRepository.Category;
import com.server.base.repository.userRepository.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "deal_log")
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@ToString
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class DealLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deal_idx", nullable = false)
    private Long dealLogNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_idx", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cate_idx")
    private Category category;

    @Column(name = "deal_date")
    private LocalDate dealDate;

    @Column(name = "deal_price", nullable = false)
    private Integer dealPrice;

    @Column(name = "deal_content", length = 500)
    private String dealContent;

    @Column(name = "is_outcome")
    private Boolean isOutcome;


    public void changeCategory(Category category){
        if(!Objects.isNull(category)&& !this.category.equals(category)){
            this.category = category;
        }
    }
    public void changeMemo(String memo){
        if(!StringUtils.isEmpty(memo)){
            this.dealContent = memo;
        }
    }
    public void changePrice(Integer price){
        if(price>=0 && !this.dealPrice.equals(price)){
            this.dealPrice = price;
        }
    }
    public void changePlusMinus(Boolean isOutcome){
        if(!Objects.isNull(isOutcome)&& !this.isOutcome.equals(isOutcome)){
            this.isOutcome = isOutcome;
        }
    }


}