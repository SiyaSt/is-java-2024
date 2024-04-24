package itmo.anastasiya.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday_date")
    private Date birthdayDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Cat> cats = new ArrayList<>();

    public Owner(String name, Date birthdayDate) {
        this.name = name;
        this.birthdayDate = birthdayDate;
    }
}
