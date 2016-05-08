package com.sample.hateoas.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.hateoas.Identifiable;

import lombok.Data;

@Entity
@Data
public class GardenGroup implements Identifiable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;


    private BigDecimal rate;

    @OneToMany
    @JoinColumn
    private List<Child> children;
    @ManyToOne
    @JoinColumn(nullable = false)
    private KinderGartener kinderGartener;

    public enum Type {

        FIRST,

        SECOND,

        THIRD

    }

}
