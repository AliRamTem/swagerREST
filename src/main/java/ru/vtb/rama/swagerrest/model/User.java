package ru.vtb.rama.swagerrest.model;


import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.vtb.rama.swagerrest.model.base.BaseDeleteEntity;

import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseDeleteEntity {


    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "id")
    private List<Vacation> vacationsList;
}
