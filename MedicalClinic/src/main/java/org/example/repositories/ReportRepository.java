package org.example.repositories;

import org.example.Appointment;
import org.example.Report;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

import java.util.List;

public class ReportRepository {

    private final Session session;

    private final Transaction transaction;


    public ReportRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }

    public void createReport(Report report) {
        try {
            session.persist(report);
            transaction.commit();

           //  session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public List<Report> getAllReports(){
        String getAll= "select reportId from Report report";
        Query<Report> getAllQuery = session.createQuery(getAll, Report.class);
        List<Report> reports = getAllQuery.getResultList();

        for (Report rep:reports) {
            System.out.println(rep.toString());

        }
        return reports;
    }


    public Report getReportById(Long id){
        Report report = session.get(Report.class, id);
        return report;
    }

    public void deleteReportById(Long id){
        Report report = getReportById(id);
        session.remove(report);
    }


}






