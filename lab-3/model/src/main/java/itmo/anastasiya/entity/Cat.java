package itmo.anastasiya.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cat")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday_date")
    private Date birthdayDate;

    @Column(name = "breed")
    private String breed;

    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "friendship",
            joinColumns = {@JoinColumn(name = "first_cat_id")},
            inverseJoinColumns = {@JoinColumn(name = "second_cat_id")}
    )
    private final List<Cat> friends = new ArrayList<>();

    public Cat(String name, Date birthdayDate, String breed, Color color, Owner owner) {
        this.name = name;
        this.birthdayDate = birthdayDate;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
    }

    public void addFriend(Cat anotherCat) {
        friends.add(anotherCat);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Cat cat = (Cat) o;
        return getId() != null && Objects.equals(getId(), cat.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
