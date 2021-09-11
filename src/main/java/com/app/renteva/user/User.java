package com.app.renteva.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuperBuilder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    @Transient
    String fullName;

    @Column
    @NotNull
    String firstName;

    @Column
    @NotNull
    String lastName;

    @Column
    @NotNull
    String password;

    @Column(unique = true)
    @NotNull
    String email;

    @Builder.Default
    Boolean active = Boolean.TRUE;

    public String getFullName() {
        return this.firstName.concat(" ").concat(this.lastName);
    }
}
