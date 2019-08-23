package com.apsidepoei.projetpoei;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.apsidepoei.projetpoei.database.DbManager;

import com.apsidepoei.projetpoei.database.entitiesgenerator.MatterGenerator;
import com.apsidepoei.projetpoei.database.entitiesgenerator.AddressGenerator;
import com.apsidepoei.projetpoei.database.entitiesgenerator.AppointmentGenerator;
import com.apsidepoei.projetpoei.database.entitiesgenerator.AssessmentGenerator;
import com.apsidepoei.projetpoei.database.entitiesgenerator.DegreeGenerator;
import com.apsidepoei.projetpoei.database.entitiesgenerator.EntrepriseGenerator;
import com.apsidepoei.projetpoei.database.entitiesgenerator.FeedbackGenerator;
import com.apsidepoei.projetpoei.database.entitiesgenerator.SessionGenerator;

import com.apsidepoei.projetpoei.entities.Address;
import com.apsidepoei.projetpoei.entities.Appointment;
import com.apsidepoei.projetpoei.entities.Assessment;
import com.apsidepoei.projetpoei.entities.Degree;
import com.apsidepoei.projetpoei.entities.Entreprise;
import com.apsidepoei.projetpoei.entities.Feedback;
import com.apsidepoei.projetpoei.entities.Matter;
import com.apsidepoei.projetpoei.entities.Session;
import com.tactfactory.consolelogger.ConsoleLogger;
import com.tactfactory.consolelogger.GlobalLogger;
import com.tactfactory.consolelogger.Options;

public final class ProjetPoeiApplication {

  private ProjetPoeiApplication() {

  }

  /**
   * Main entry.
   * @param args = the args
   * @throws ParseException = exception
   * @throws SQLException   = exception
   */
  public static void main(String[] args) throws ParseException, SQLException {
    ConsoleLogger releaseLogger = new ConsoleLogger("zBleugin Application", Options.RELEASE);
    releaseLogger.Log("Lancement de l'application.", Options.RELEASE, true);

    /**
     * Tests des différents loggers.
     * Les loggers créent des fichiers de log
     */
//    GlobalLogger.getConsoleLogger().Log("ma deuxième data", Options.DEBUG);
//    GlobalLogger.getConsoleLogger().Log("ma deuxième data", Options.DEBUG, true);
//    GlobalLogger.getConsoleLogger().Log("ma deuxième data", Options.ERROR);
//    GlobalLogger.getConsoleLogger().Log("ma deuxième data", Options.ERROR, true);
//    GlobalLogger.getConsoleLogger().Log("Ma data", Options.WARNING);
//    GlobalLogger.getConsoleLogger().Log("Ma data", Options.WARNING, true);
//    GlobalLogger.getConsoleLogger().Log("ma deuxième data", Options.RELEASE);
//    GlobalLogger.getConsoleLogger().Log("ma deuxième data", Options.RELEASE, true);
//    GlobalLogger.getConsoleLogger().changeOption(Options.RELEASE);

    ConsoleLogger warnLogger = new ConsoleLogger("ZBleugin Application", Options.WARNING);
    warnLogger.Log("Désactivez les tests avant la release.", Options.WARNING, true);

//    // Génération de données
//    functionalLogger.Log("Tests de génération de données Matter et Assessment", Options.DEBUG);
//    testGenerate();

    // Test entité Assessment
    assessmentTests();
    AssessmentGenerator.getInstance().generateAndInsertDatas();

//    // Tests entité Address
//    functionalLogger.Log("Tests de Address.", Options.DEBUG);
//    addressTests();
//    AddressGenerator.getInstance().generateAndInsertDatasDroppingTable(10);
//    AddressGenerator.getInstance().deleteDatas();
//
//    // Tests entité Feedback
//    functionalLogger.Log("Tests de Feedback.", Options.DEBUG);
//    feedbackTests();
//    FeedbackGenerator.getInstance().generateAndInsertDatasDroppingTable(9);
//    FeedbackGenerator.getInstance().deleteDatas();
//
//    // Test entité Entreprise
//    functionalLogger.Log("Tests de  Entreprise.", Options.DEBUG);
//    entrepriseTests();
//    EntrepriseGenerator.getInstance().generateAndInsertDatasDroppingTable(10);
//
//    // Tests entité Matter
//    functionalLogger.Log("Tests de Matter.", Options.DEBUG);
//    mattersTests();
//
//    // Tests entité Degree
//    functionalLogger.Log("Tests de Degree.", Options.DEBUG);
//    degreeTests();
//    DegreeGenerator.getInstance().generateAndInsertDatasDroppingTable(10);
//
//    // Tests entité Appointment
//    functionalLogger.Log("Tests de Appointment.", Options.DEBUG);
//    appointmentTests();
//    AppointmentGenerator.getInstance().generateAndInsertDatasDroppingTable(10);
//
//    // Tests entité Session
//    functionalLogger.Log("Tests de Session.", Options.DEBUG);
//    sessionTests();
//    SessionGenerator.getInstance().generateAndInsertDatasDroppingTable(10);

  }

  private static final void addressTests() throws SQLException {
    ConsoleLogger functionalLogger = new ConsoleLogger("Address functionals tests", Options.DEBUG);
    functionalLogger.Log("Lancement des tests.", Options.DEBUG);

    DbManager.getInstance().getAddressDao().drop();
    DbManager.getInstance().getAddressDao().create();
    Address address1 = new Address("51 rue de l'alma", "35000", "rennes");
    DbManager.getInstance().getAddressDao().insert(address1);

    Address address2 = new Address("87 rue nationale", "35130", "chartres");
    DbManager.getInstance().getAddressDao().insert(address2);

    Address address3 = new Address("ZI des basses forges", "35530", "noyal sur vilaine");
    DbManager.getInstance().getAddressDao().insert(address3);

    for (Object obj : DbManager.getInstance().getAddressDao().select()) {
      System.out.println(obj.toString());
    }
    DbManager.getInstance().getAddressDao().delete(address1);
    address2.setPostalCode("35531");
    DbManager.getInstance().getAddressDao().update(address2);

    for (Object obj : DbManager.getInstance().getAddressDao().select()) {
      System.out.println(obj.toString());
    }

    functionalLogger.Log("Fin des tests.", Options.DEBUG);
  }

  /**
   * Functions tests for degree.
   * @throws SQLException   = exception
   * @throws ParseException = exception
   */
  private static void appointmentTests() throws SQLException, ParseException {
    ConsoleLogger functionalLogger = new ConsoleLogger("Appointment functionals tests", Options.DEBUG);
    functionalLogger.Log("Lancement des tests.", Options.DEBUG);

    SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    final Date mySqlDate = sdf.parse("2019-08-20 02:05:00");
    final Date mySqlDateChg = sdf.parse("2019-09-12 06:05:00");

    DbManager.getInstance().getAppointmentDao().drop();
    DbManager.getInstance().getAppointmentDao().create();
    Appointment appointment1 = new Appointment("Information de rendez-vous M. Bon", mySqlDate,
        "Compte rendu de rendez-vous de M. Jean");
    DbManager.getInstance().getAppointmentDao().insert(appointment1);

    Appointment appointment2 = new Appointment("Information de rendez-vous M. Jean", mySqlDate,
        "Compte rendu de rendez-vous de M. Bon");
    DbManager.getInstance().getAppointmentDao().insert(appointment2);

    for (Object obj : DbManager.getInstance().getAppointmentDao().select()) {
      System.out.println(obj.toString());
    }

    DbManager.getInstance().getAppointmentDao().delete(appointment1);

    for (Object obj : DbManager.getInstance().getAppointmentDao().select()) {
      System.out.println(obj.toString());
    }

    appointment2.setDateTime(mySqlDateChg);
    DbManager.getInstance().getAppointmentDao().update(appointment2);

    for (Object obj : DbManager.getInstance().getAppointmentDao().select()) {
      System.out.println(obj.toString());
    }

    functionalLogger.Log("Fin des tests.", Options.DEBUG);
  }

  private static final void feedbackTests() throws SQLException {
    ConsoleLogger functionalLogger = new ConsoleLogger("Feedback functionals tests", Options.DEBUG);
    functionalLogger.Log("Lancement des tests.", Options.DEBUG);

    DbManager.getInstance().getFeedbackDao().drop();
    DbManager.getInstance().getFeedbackDao().create();
    Feedback feedback1 = new Feedback("CDI", 0, "en contrat depuis sa fin de formation");
    DbManager.getInstance().getFeedbackDao().insert(feedback1);

    Feedback feedback2 = new Feedback("CDD", 12, "en contrat");
    DbManager.getInstance().getFeedbackDao().insert(feedback2);

    Feedback feedback3 = new Feedback("CDD", 18, "en contrat depuis 3 mois");
    DbManager.getInstance().getFeedbackDao().insert(feedback3);

    for (Object obj : DbManager.getInstance().getFeedbackDao().select()) {
      System.out.println(obj.toString());
    }
    DbManager.getInstance().getFeedbackDao().delete(feedback1);
    feedback2.setDurationOfContract(24);
    DbManager.getInstance().getFeedbackDao().update(feedback2);

    for (Object obj : DbManager.getInstance().getFeedbackDao().select()) {
      System.out.println(obj.toString());
    }

    functionalLogger.Log("Fin des tests.", Options.DEBUG);
  }

  /** Test of database function formatters class. */
  private static final void mattersTests() throws SQLException {
    ConsoleLogger functionalLogger = new ConsoleLogger("Matter functionals tests", Options.DEBUG);
    functionalLogger.Log("Lancement des tests.", Options.DEBUG);

    DbManager.getInstance().getMatterDao().drop();
    DbManager.getInstance().getMatterDao().create();
    Matter matters1 = new Matter("Physique Quantique");
    DbManager.getInstance().getMatterDao().insert(matters1);

    Matter matters2 = new Matter("Algorythmie");
    DbManager.getInstance().getMatterDao().insert(matters2);

    for (Object obj : DbManager.getInstance().getMatterDao().select()) {
      System.out.println(obj.toString());
    }
    DbManager.getInstance().getMatterDao().delete(matters1);

    for (Object obj : DbManager.getInstance().getMatterDao().select()) {
      System.out.println(obj.toString());
    }
    matters2.setName("Mathematique");
    DbManager.getInstance().getMatterDao().update(matters2);

    for (Object obj : DbManager.getInstance().getMatterDao().select()) {
      System.out.println(obj.toString());
    }

    functionalLogger.Log("Fin des tests.", Options.DEBUG);
  }

  /**
   * Functions tests for degree.
   * @throws SQLException = exception
   */
  private static final void degreeTests() throws SQLException {
    ConsoleLogger functionalLogger = new ConsoleLogger("Degree functionals tests", Options.DEBUG);
    functionalLogger.Log("Lancement des tests.", Options.DEBUG);

    DbManager.getInstance().getDegreeDao().drop();
    DbManager.getInstance().getDegreeDao().create();
    Degree degree1 = new Degree("BTS Informatique", "BAC + 2");
    DbManager.getInstance().getDegreeDao().insert(degree1);

    Degree degree2 = new Degree("BTS Systèmes Numériques", "BAC + 2");
    DbManager.getInstance().getDegreeDao().insert(degree2);

    for (Object obj : DbManager.getInstance().getDegreeDao().select()) {
      System.out.println(obj.toString());
    }

    DbManager.getInstance().getDegreeDao().delete(degree1);

    for (Object obj : DbManager.getInstance().getDegreeDao().select()) {
      System.out.println(obj.toString());
    }

    degree2.setName("BTS Systèmes Numériques Option A");
    DbManager.getInstance().getDegreeDao().update(degree2);

    for (Object obj : DbManager.getInstance().getDegreeDao().select()) {
      System.out.println(obj.toString());
    }

    functionalLogger.Log("Fin des tests.", Options.DEBUG);
  }

  private static final void entrepriseTests() throws SQLException {
    ConsoleLogger functionalLogger = new ConsoleLogger("Entreprise functionals tests", Options.DEBUG);
    functionalLogger.Log("Lancement des tests.", Options.DEBUG);

    DbManager.getInstance().getEntrepriseDao().drop();
    DbManager.getInstance().getEntrepriseDao().create();
    Entreprise entreprise1 = new Entreprise("Apside", "Apside Rennes", "30906508400068", "0000A");
    DbManager.getInstance().getEntrepriseDao().insert(entreprise1);

    Entreprise entreprise2 = new Entreprise("Capgemini", "Capgemini Nantes", "33070384400036", "0000A");
    DbManager.getInstance().getEntrepriseDao().insert(entreprise2);

    for (Object obj : DbManager.getInstance().getEntrepriseDao().select()) {
      System.out.println(obj.toString());
    }

    DbManager.getInstance().getEntrepriseDao().delete(entreprise1);

    for (Object obj : DbManager.getInstance().getEntrepriseDao().select()) {
      System.out.println(obj.toString());
    }

    entreprise2.setNom("Capgemini France");
    DbManager.getInstance().getEntrepriseDao().update(entreprise2);

    for (Object obj : DbManager.getInstance().getEntrepriseDao().select()) {
      System.out.println(obj.toString());
    }

    functionalLogger.Log("Fin des tests.", Options.DEBUG);
  }

  private static final void assessmentTests() throws SQLException, ParseException {
    ConsoleLogger functionalLogger = new ConsoleLogger("Assessment functionals tests", Options.DEBUG);
    functionalLogger.Log("Lancement des tests.", Options.DEBUG);

    DbManager.getInstance().getAssessmentDao().drop();
    DbManager.getInstance().getAssessmentDao().create();

    Assessment assessment1 = new Assessment("Riri", new SimpleDateFormat("yyyy/MM/dd").parse("1999/12/31"));
    DbManager.getInstance().getAssessmentDao().insert(assessment1);

    Assessment assessment2 = new Assessment("Fifi", new SimpleDateFormat("yyyy/MM/dd").parse("1982/02/12"));
    DbManager.getInstance().getAssessmentDao().insert(assessment2);

    for (Object obj : DbManager.getInstance().getAssessmentDao().select()) {
      System.out.println(obj.toString());
    }
    DbManager.getInstance().getAssessmentDao().delete(assessment1);
    assessment2.setCategory("Causette");
    DbManager.getInstance().getAssessmentDao().update(assessment2);

    for (Object obj : DbManager.getInstance().getAssessmentDao().select()) {
      System.out.println(obj.toString());
    }

    functionalLogger.Log("Fin des tests.", Options.DEBUG);
  }

  private static void testGenerate() throws SQLException, ParseException {

    MatterGenerator.getInstance().generateAndInsertDatasDroppingTable(10);

    for (Matter matters : DbManager.getInstance().getMatterDao().select()) {
      System.out.println(matters);
    }

    MatterGenerator.getInstance().deleteDatas();
    AssessmentGenerator.getInstance().generateAndInsertDatasDroppingTable(10);

    for (Assessment assessment : DbManager.getInstance().getAssessmentDao().select()) {
      System.out.println(assessment);
    }

    AssessmentGenerator.getInstance().deleteDatas();
  }

  /**
   * Functions tests for Session.
   *
   * @throws SQLException = exception
   * @throws ParseException = exception
   */
  private static void sessionTests() throws SQLException, ParseException {
    ConsoleLogger functionalLogger = new ConsoleLogger("Session functionals tests", Options.DEBUG);
    functionalLogger.Log("Lancement des tests.", Options.DEBUG);

    DbManager.getInstance().getSessionDao().drop();
    DbManager.getInstance().getSessionDao().create();
    Session session1 = new Session("Java Web",
        new SimpleDateFormat("yyyy/MM/dd").parse("2019/06/15"),
        new SimpleDateFormat("yyyy/mm/dd").parse("2019/10/02"));
    DbManager.getInstance().getSessionDao().insert(session1);


    Session session2 = new Session("PHP",
        new SimpleDateFormat("yyyy/MM/dd").parse("2019/11/15"),
        new SimpleDateFormat("yyyy/mm/dd").parse("2019/12/02"));
    DbManager.getInstance().getSessionDao().insert(session2);

    for (Object obj : DbManager.getInstance().getSessionDao().select()) {
      System.out.println(obj.toString());
    }

    DbManager.getInstance().getSessionDao().delete(session1);

    for (Object obj : DbManager.getInstance().getSessionDao().select()) {
      System.out.println(obj.toString());
    }

    session2.setName("PHP mySql");
    DbManager.getInstance().getSessionDao().update(session2);

    for (Object obj : DbManager.getInstance().getSessionDao().select()) {
      System.out.println(obj.toString());
    }

    functionalLogger.Log("Fin des tests.", Options.DEBUG);
  }

}
