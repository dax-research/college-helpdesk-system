package com.ddu.college_helpdesk_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    private String enrollmentNo;
    private String branch;
    private Integer semester;

    @OneToMany(mappedBy = "raisedBy", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
