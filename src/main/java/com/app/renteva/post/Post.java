package com.app.renteva.post;

import com.app.renteva.place.Place;
import com.app.renteva.user.owner.Owner;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;


@Entity
@Table(name = "post")
@Builder
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    OffsetDateTime createdAt;

    @NotNull
    @OneToOne
    Place place;

    @NotNull
    @ManyToOne
    @JoinColumn
    Owner creator;

    @NotNull
    String title;

    String comments;

    @Builder.Default
    Boolean active = Boolean.TRUE;
}
