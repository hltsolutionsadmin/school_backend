package com.hlt.usermanagement.model;

import com.hlt.usermanagement.dto.enums.TicketStatus;
import com.hlt.usermanagement.dto.enums.TicketType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets",
        indexes = {
                @Index(name = "idx_created_by", columnList = "created_by_id"),
                @Index(name = "idx_ticket_type", columnList = "type"),
                @Index(name = "idx_ticket_status", columnList = "status"),
                @Index(name = "idx_user_id", columnList = "user_id"),
                @Index(name = "idx_academic_id", columnList = "academic_id"),
                @Index(name = "idx_b2b_unit_id", columnList = "b2b_unit_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by_id", nullable = false)
    private UserModel createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_id")
    private AcademicModel academic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b2b_unit_id")
    private B2BUnitModel b2bUnit;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 50)
    private TicketType type;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private TicketStatus status;
}
