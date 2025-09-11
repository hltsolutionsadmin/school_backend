package com.hlt.usermanagement.model;

import com.hlt.usermanagement.utils.EncryptedStringConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "USER",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"USERNAME"}),
                @UniqueConstraint(columnNames = {"EMAIL"}),
                @UniqueConstraint(columnNames = {"EMAIL_HASH"}),
                @UniqueConstraint(columnNames = {"PRIMARY_CONTACT"}),
                @UniqueConstraint(columnNames = {"PRIMARY_CONTACT_HASH"})
        },
        indexes = {
                @Index(name = "idx_username", columnList = "USERNAME"),
                @Index(name = "idx_email", columnList = "EMAIL"),
                @Index(name = "idx_email_hash", columnList = "EMAIL_HASH"),
                @Index(name = "idx_primary_contact", columnList = "PRIMARY_CONTACT"),
                @Index(name = "idx_mobile_hash", columnList = "PRIMARY_CONTACT_HASH")
        }
)
@Getter
@Setter
public class UserModel extends AuditableModel {

    @NotBlank
    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Size(max = 20)
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Email
    @Size(max = 50)
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "EMAIL_HASH", nullable = false, unique = true)
    private String emailHash;

    @NotBlank
    @Column(name = "PRIMARY_CONTACT", nullable = false, unique = true)
    private String primaryContact;

    @Column(name = "PRIMARY_CONTACT_HASH", nullable = false, unique = true)
    private String primaryContactHash;

    @Convert(converter = EncryptedStringConverter.class)
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "PROFILE_PICTURE_ID")
    private Long profilePictureId;

    @Column(name = "FCM_TOKEN")
    private String fcmToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_ID")
    private SchoolModel school;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<RoleModel> roles = new HashSet<>();

    @Column(name = "PROFILE_COMPLETED", nullable = false)
    private Boolean profileCompleted = Boolean.FALSE;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressModel> addresses = new ArrayList<>();
}
