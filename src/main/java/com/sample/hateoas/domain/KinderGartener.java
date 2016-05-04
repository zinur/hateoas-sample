package com.sample.hateoas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.hateoas.Identifiable;

import lombok.Data;

/**
 * @author zinur mustafayev
 */
@Entity
@Data
public class KinderGartener implements Identifiable<Long> {


    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String phoneNumber;
}
