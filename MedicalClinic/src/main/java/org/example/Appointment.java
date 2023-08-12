package org.example;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "Appointment")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Appointment {
   @Id
   @Column(name = "Id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "Patient_Name")
   private String patientName;
   @Column(name = "Note")
   private  String note;
   @Column (name = "Status")
   private String status;
   @Column(name = "Begin_at")
   private LocalDateTime beginAt;
   @Column(name = "Ends_at")
   private LocalDateTime endsAt;

@ManyToOne @JoinColumn(name = "doctor_Id"/*,referencedColumnName = "doctor_id"*/)
   private Doctor doctor;
@OneToOne(mappedBy = "appointment")
   private Report report;


}
