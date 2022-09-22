package org.san.home.persons.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Slf4j
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "person", uniqueConstraints = @UniqueConstraint(columnNames = {"first_name", "second_name", "third_name"}))
public class Person {
    @Id
    @Getter
    @Setter
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    @Unique
    @GeneratedValue
    private Long globalId;

    @NotNull
    @Getter
    @Setter
    @Length(min = 1, max = 50)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Getter
    @Setter
    @Length(min = 1, max = 50)
    @Column(name = "second_name")
    private String secondName;

    @Getter
    @Setter
    @Length(min = 1, max = 50)
    @Column(name = "third_name")
    private String thirdName;
}
