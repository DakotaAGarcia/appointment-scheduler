package com.devmountain.appointmentScheduler.entities;

import com.devmountain.appointmentScheduler.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String password;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private Set<Appointment> appointmentSet = new HashSet<>();

    public User(UserDto userDto){
        if(userDto.getUsername() != null)
            this.username = userDto.getUsername();
        if(userDto.getPassword() != null)
            this.password = userDto.getPassword();
        if(userDto.getEmail() != null){
            this.email = userDto.getEmail();
        }
    }

}
