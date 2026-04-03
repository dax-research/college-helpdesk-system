package com.ddu.college_helpdesk_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "user_id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student extends User {

    @Column(unique = true, nullable = false)
    private String enrollmentNo;
    private String branch;
    private Integer semester;

    @JsonIgnore
    @OneToMany(mappedBy = "raisedBy", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
