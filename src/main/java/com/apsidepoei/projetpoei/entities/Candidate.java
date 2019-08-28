/**
 *
 */
package com.apsidepoei.projetpoei.entities;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.apsidepoei.projetpoei.database.contracts.CandidateContract;
import com.apsidepoei.projetpoei.database.contracts.DegreeContract;
import com.apsidepoei.projetpoei.database.contracts.FeedbackContract;
import com.apsidepoei.projetpoei.database.contracts.MatterContract;
import com.apsidepoei.projetpoei.database.contracts.SessionContract;
import com.apsidepoei.projetpoei.entities.RankingCandidate;
import com.apsidepoei.projetpoei.entities.Person;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author vianney
 *
 */
@Entity
@Table(name = CandidateContract.TABLE)
@AttributeOverride(name = "id", column = @Column(name = CandidateContract.COL_ID))
public class Candidate extends Person {

  @JsonProperty(value = CandidateContract.COL_RANKING_CANDIDATE)
  @Column(name = CandidateContract.COL_RANKING_CANDIDATE, nullable = true)
  private RankingCandidate ranking;

  @JsonProperty(value = CandidateContract.COL_FK_ID_FEEDBACK)
  @ManyToOne(targetEntity=Feedback.class,optional=true)
  @JoinColumn(name=CandidateContract.COL_FK_ID_FEEDBACK, referencedColumnName=FeedbackContract.COL_ID)
  private Feedback feedback;

  @JsonProperty(value = CandidateContract.COL_DEGREES)
  @ManyToMany(targetEntity = Degree.class)
  @JoinTable(name = "candidate_degree", joinColumns = {
      @JoinColumn(name = CandidateContract.COL_ID) }, inverseJoinColumns = {
          @JoinColumn(name = DegreeContract.COL_ID) })
  private List<Degree> degrees;

  @JsonProperty(value = CandidateContract.COL_MATTERS)
  @ManyToMany(targetEntity = Matter.class)
  @JoinTable(name = "candidate_matter", joinColumns = {
      @JoinColumn(name = CandidateContract.COL_ID) }, inverseJoinColumns = {
          @JoinColumn(name = MatterContract.COL_ID) })
  private List<Matter> matters;

  @JsonProperty(value = CandidateContract.COL_SESSIONS)
  @ManyToMany(targetEntity = Session.class)
  @JoinTable(name = "candidate_session", joinColumns = {
      @JoinColumn(name = CandidateContract.COL_ID) }, inverseJoinColumns = {
          @JoinColumn(name = SessionContract.COL_ID) })
  private List<Session> sessions;

  @JsonProperty(value = CandidateContract.COL_ASSESSMENTS)
  @OneToMany(targetEntity=Assessment.class)
  private List<Assessment> assessments;


  /**
   * Empty constructor.
   */
  public Candidate() {
  }

  /**
   * Constructor with id for new Feedback.
   *
   * @param firstname = the firstname
   * @param lastname  = the lastname
   * @param email     = the email
   * @param cellPhone = the cellPhone
   */
  public Candidate(String firstname, String lastname, String email, String cellPhone) {
    super();
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.cellPhone = cellPhone;
  }

  /**
   * Override toString() function.
   */
  @Override
  public String toString() {
    return "Candidate [" + "Id = " + getId() + ", prénom = " + firstname + ", nom = " + lastname
        + ", rang = "+ ranking.label + ", email = " + email + ", téléphone = " + cellPhone + "]";
  }


  // GETTER/SETTER


  /**
   * @return the ranking
   */
  public RankingCandidate getRanking() {
    return ranking;
  }

  /**
   * @param ranking the ranking to set
   */
  public void setRanking(RankingCandidate ranking) {
    this.ranking = ranking;
  }

  /**
   * @return the feedback
   */
  public Feedback getFeedback() {
    return feedback;
  }

  /**
   * @param feedback the feedback to set
   */
  public void setFeedback(Feedback feedback) {
    this.feedback = feedback;
  }

  /**
   * @return the degrees
   */
  public List<Degree> getDegrees() {
    return degrees;
  }

  /**
   * @param degrees the degrees to set
   */
  public void setDegrees(List<Degree> degrees) {
    this.degrees = degrees;
  }

  /**
   * @return the matters
   */
  public List<Matter> getMatters() {
    return matters;
  }

  /**
   * @param matters the matters to set
   */
  public void setMatters(List<Matter> matters) {
    this.matters = matters;
  }

  /**
   * @return the sessions
   */
  public List<Session> getSessions() {
    return sessions;
  }

  /**
   * @param sessions the sessions to set
   */
  public void setSessions(List<Session> sessions) {
    this.sessions = sessions;
  }

  /**
   * @return the assessments
   */
  public List<Assessment> getAssessments() {
    return assessments;
  }

  /**
   * @param assessments the assessments to set
   */
  public void setAssessments(List<Assessment> assessments) {
    this.assessments = assessments;
  }
}
