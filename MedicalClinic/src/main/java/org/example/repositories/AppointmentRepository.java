package org.example.repositories;

import org.example.Appointment;
import org.example.Doctor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentRepository {

    private final Session session;
    private final Transaction transaction;


    public AppointmentRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }
    public List<Appointment> getAll(){
        String query = "from Appointment";
        Query<Appointment> getAll = session.createQuery(query, Appointment.class);
        return getAll.getResultList();

    }
    public Appointment getbyId (Long id){
        return session.get(Appointment.class, id);
    }

    public void create(Appointment appointment) {
        try {
            session.persist(appointment);
            transaction.commit();

            // session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public  Appointment update(Appointment appointment){
        if (appointment.getId()!=null){
            try{
                session.persist(appointment);
                transaction.commit();
            }catch (Exception e){
                transaction.rollback();
                e.printStackTrace();
            }
        }return appointment;
    }

    public void deleteById(Long id){
        Appointment appointment=getbyId(id);
        if (appointment!=null){
            try {
                session.remove(appointment);
                transaction.commit();
            }catch (Exception e){
                transaction.rollback();
                e.printStackTrace();
            }

        }

    }

    public  List<Appointment>getAllByDoctorAfterNow (Long id){

        String query="select appointment from Appointment appointment left join " +
                "fetch appointment.doctor doctor where doctor.doctorId=:id and appointment.beginAt>: now";
        Query<Appointment>getAllByDoctor=session.createQuery(query, Appointment.class);
        getAllByDoctor.setParameter("id",id);
        getAllByDoctor.setParameter("now", LocalDateTime.now());

        return getAllByDoctor.getResultList();
    }

    }
