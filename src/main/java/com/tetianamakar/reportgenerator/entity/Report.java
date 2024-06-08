package com.tetianamakar.reportgenerator.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "report_date")
    private Timestamp reportDate;

    @Column(name = "total_revenue")
    private BigDecimal totalRevenue;

    @Column(name = "net_profit")
    private BigDecimal netProfit;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

}
