package org.example.configuration;

import org.example.Appointment;
import org.example.Doctor;
import org.example.Report;
import org.example.User;
import org.example.repositories.AppointmentRepository;
import org.example.repositories.DoctorRepository;
import org.example.repositories.ReportRepository;
import org.example.repositories.UserRepository;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            //.addAnnotatedClass(Actors.class);
           configuration.addAnnotatedClass(Appointment.class);
           configuration.addAnnotatedClass(Doctor.class);
           configuration.addAnnotatedClass(Report.class);
           configuration.addAnnotatedClass(User.class);
           configuration.addAnnotatedClass(AppointmentRepository.class);
           configuration.addAnnotatedClass(DoctorRepository.class);
           configuration.addAnnotatedClass(ReportRepository.class);
           configuration.addAnnotatedClass(UserRepository.class);

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
