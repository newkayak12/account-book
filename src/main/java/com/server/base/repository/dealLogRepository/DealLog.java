package com.server.base.repository.dealLogRepository;

import com.server.base.repository.categoryRepository.Category;
import com.server.base.repository.userRepository.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "deal_log")
public class DealLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deal_idx", nullable = false)
    private Long dealLogNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_idx", nullable = false)
    private User userIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cate_idx")
    private Category cateIdx;

    @Column(name = "deal_date")
    private LocalDate dealDate;

    @Column(name = "deal_price", nullable = false)
    private Integer dealPrice;

    @Column(name = "deal_content", length = 500)
    private String dealContent;

    @Column(name = "is_outcome")
    private Boolean isOutcome;



}