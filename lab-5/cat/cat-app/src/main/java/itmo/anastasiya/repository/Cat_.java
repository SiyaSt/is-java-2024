package itmo.anastasiya.repository;

import itmo.anastasiya.entity.Cat;
import itmo.anastasiya.entity.Color;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;


@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cat.class)
public class Cat_ {
    public static volatile SingularAttribute<Cat, Long> ownerId;
    public static volatile SingularAttribute<Cat, String> breed;
    public static volatile SingularAttribute<Cat, Color> color;
    public static volatile SingularAttribute<Cat, Long> id;
    public static final String OWNER = "owner";
    public static final String BREED = "breed";
    public static final String COLOR = "color";
    public static final String ID = "id";
}
