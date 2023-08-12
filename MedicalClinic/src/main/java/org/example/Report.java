package org.example;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "REPORT")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Report {
    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "Report_Id")
    private Long reportId;
    @Column(name = "Description")
    private String description;

    @OneToOne
    @JoinColumn(name = "appointment_Id")
    private Appointment appointment;

    public Report(Appointment appointment) {
        this.appointment = appointment;
    }
}
