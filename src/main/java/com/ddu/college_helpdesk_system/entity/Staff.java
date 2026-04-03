package com.ddu.college_helpdesk_system.entity;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "staff")
@PrimaryKeyJoinColumn(name = "user_id")
public class Staff extends User {

    @Column(unique = true, nullable = false)
    private String employeeId;
    private String designation;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "resolvedBy", cascade = CascadeType.ALL)
    private List<TicketResponse> responses;
}