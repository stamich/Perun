package codeswarm.io.perun.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "`user`")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractModel<Long> {

    public enum Role {
    ADMIN,
    READER,
    WRITER;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Transient
    private String passwordConfirmation;

    @Column(nullable = false, unique = true)
    private String token;

    @ElementCollection
    @SortNatural
    @JoinTable(name = "role")
    @Enumerated(EnumType.STRING)
    @Column(name = "roles", length = 20, nullable = false)
    private SortedSet<Role> roles = new TreeSet<>();

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false)
    private Boolean confirmed;
}
