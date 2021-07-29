package com.bearman.demo.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_address")
public class Address extends BaseEntity {

    @Column(length = 120)
    private String line1;

    @JsonIgnore
    @Column(length = 120)
    private String line2;

    @Column(length = 120)
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "name_user_id", nullable = false)
    private User user;
}
