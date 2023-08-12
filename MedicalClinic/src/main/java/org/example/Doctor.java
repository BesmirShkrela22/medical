package org.example;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "Doctor")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctor {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "doctor_id")
    private Long doctorId;
    @Column(name ="username" )
    private  String username;
    @Column(name = "doctor_name")
    private String doctorName;
    @Column(name ="Specialization" )
    private String specialization;

@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    List<Appointment> appointmentList;


    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", username='" + username + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }

    public Doctor(String username, String doctorName, String specialization) {
        this.username = username;
        this.doctorName = doctorName;
        this.specialization = specialization;
    }
}
