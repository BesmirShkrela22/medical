package org.example.repositories;

import org.example.Doctor;
import org.example.configuration.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DoctorRepository {

    private Session session;
    private Transaction transaction;

    public DoctorRepository(Session session, Transaction transaction){
        this.session = session;
        this.transaction = transaction;
    }

    public void insertDoctor(Doctor doctor) {
        try {
            session.persist(doctor);
            transaction.commit();

           // session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateDoctor(Doctor doctor) {
        try {
            if (doctor.getDoctorId()!=null) {
                session.persist(doctor);
                transaction.commit();
                System.out.println("U kry");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Doctor getById(Integer doctorId) {
        String hql = "from Doctor where doctorId = :doctorId";
        Query query = session.createQuery(hql);
        query.setParameter("doctorId", doctorId);

        List<Doctor> listDoctors = query.list();

        for (Doctor doctor : listDoctors) {
            System.out.println(doctor.toString());
        }

        if (!listDoctors.isEmpty()) {
            return listDoctors.get(0);
        } else {
            System.out.println("Doctor id not found");
            return null;

        }
    }


  /*  public Doctor getById(Integer doctorId){

        String hql = "from Doctor where doctorId = :doctorId";
        Query query = session.createQuery(hql);
        List<Doctor> listDoctors = query.list();

        for (Doctor doctor : listDoctors) {
            System.out.println(doctor.toString());
        }


      return session.get(Doctor.class, doctorId);

    }*/

    public void deleteDoctors(String remuveid){
        try {
            Query delQuery = session.createQuery("delete from Doctor where id=:id");
            delQuery.setParameter("id", remuveid);
            int result = delQuery.executeUpdate();
            transaction.commit();


            System.out.println("No of rows updated: " + result);

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void selectAllDoctors() {

        try {
            Query<Doctor> selQuery = session.createQuery("from Doctor", Doctor.class);
            List<Doctor> doctorList = selQuery.getResultList();
            System.out.println("list of application size is:" + doctorList.size());
            doctorList.forEach(doctor -> {
                System.out.println(doctor.toString());
            });
            // njelloj me lart
//            for (Doctor doctor : doctorList) {
//                System.out.println(doctor.toString());
//            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }


    public List<Doctor> getAllBySpecialization(String specialization) {

        List<Doctor> doctors = null;
        try {
            String query = "select doctor from Doctor doctor where doctor.specialization = :spec";
            Query<Doctor> getBySpecialization = session.createQuery(query, Doctor.class);

            getBySpecialization.setParameter("spec", specialization);
            doctors = getBySpecialization.getResultList();
            for (Doctor d:doctors
                 ) {
                System.out.println(d);

            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return doctors;
    }


}






