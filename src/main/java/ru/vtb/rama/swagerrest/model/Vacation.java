package ru.vtb.rama.swagerrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.vtb.rama.swagerrest.model.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "vacations")
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Vacation extends BaseEntity {

    @Column(name = "begin_date")
    private LocalDate begin;

    @Column(name = "end_date")
    private LocalDate end;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}