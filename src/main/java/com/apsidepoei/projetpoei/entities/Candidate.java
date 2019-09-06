/**
 *
 */
package com.apsidepoei.projetpoei.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.apsidepoei.projetpoei.database.contracts.AddressContract;
import com.apsidepoei.projetpoei.database.contracts.CandidateContract;
import com.apsidepoei.projetpoei.database.contracts.DegreeContract;
import com.apsidepoei.projetpoei.database.contracts.MatterContract;
import com.apsidepoei.projetpoei.database.contracts.SessionContract;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vianney
 *
 */
@Entity
@Table(name = CandidateContract.TABLE)
public class Candidate extends Person {

  @JsonProperty(value = CandidateContract.COL_RANKING_CANDIDATE)
  @Column(name = CandidateContract.COL_RANKING_CANDIDATE, nullable = true)
  private RankingCandidate ranking = RankingCandidate.RANK_0;

  @JsonProperty(value = CandidateContract.COL_FK_ID_FEEDBACK)
//  @ManyToOne(targetEntity = Feedback.class, optional = true)
  @ManyToOne()
//  @JoinColumn(name = CandidateContract.COL_FK_ID_FEEDBACK, referencedColumnName = FeedbackContract.COL_ID)
  private Feedback feedback;

  @JsonProperty(value = CandidateContract.COL_DEGREES)
//  @ManyToMany(targetEntity = Degree.class)
//  @JoinTable(name = "candidate_degree", joinColumns = {
//      @JoinColumn(name = CandidateContract.COL_ID) }, inverseJoinColumns = {
//          @JoinColumn(name = DegreeContract.COL_ID) })
  @ManyToMany(fetch = FetchType.EAGER,
  cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinTable(name = "candidate_degree",
          joinColumns = { @JoinColumn( name = CandidateContract.COL_ID) },
          inverseJoinColumns = { @JoinColumn(name = DegreeContract.COL_ID) })
  private List<Degree> degrees = new ArrayList<>();

  @JsonProperty(value = CandidateContract.COL_MATTERS)
  @ManyToMany(targetEntity = Matter.class)
  @JoinTable(name = "candidate_matter", joinColumns = {
      @JoinColumn(name = CandidateContract.COL_ID) }, inverseJoinColumns = {
          @JoinColumn(name = MatterContract.COL_ID) })
  private List<Matter> matters = new ArrayList<>();

  @JsonProperty(value = CandidateContract.COL_SESSIONS)
  @ManyToMany(targetEntity = Session.class)
  @JoinTable(name = "candidate_session", joinColumns = {
      @JoinColumn(name = CandidateContract.COL_ID) }, inverseJoinColumns = {
          @JoinColumn(name = SessionContract.COL_ID) })
  private List<Session> sessions = new ArrayList<>();
//
  @JsonProperty(value = CandidateContract.COL_FK_ID_ADDRESS)
  @ManyToOne(targetEntity = Address.class, optional = true)
  @JoinColumn(name = CandidateContract.COL_FK_ID_ADDRESS, referencedColumnName = AddressContract.COL_ID)
//  @ManyToOne()
  protected Address address;

  /**
   * Empty constructor.
   */
  public Candidate() {
    super();
  }

  /**
   * Constructor with id for new Feedback.
   *
   * @param firstname   = the firstname
   * @param lastname    = the lastname
   * @param email       = the email
   * @param cellPhone   = the cellPhone
   */
  public Candidate(String firstname, String lastname, String email, String cellPhone) {
    super(firstname, lastname, email, cellPhone);
  }

  /**
   * @param ranking
   * @param feedback
   * @param degrees
   * @param matters
   * @param sessions
   */
  public Candidate(String firstname, String lastname, String email, String cellPhone, String homePhone, String commentary, Boolean mainContact, Address address, RankingCandidate ranking, Feedback feedback, List<Degree> degrees,
      List<Matter> matters, List<Session> sessions) {
    super(firstname, lastname, email, cellPhone, homePhone, commentary, mainContact);
    this.ranking = ranking;
    this.feedback = feedback;
    this.degrees = degrees;
    this.matters = matters;
    this.sessions = sessions;
    this.address = address;
  }


  /**
   * Override toString() function.
   */
  @Override
  public String toString() {
    return "Candidate [" + "Id = " + this.getId() + ", prénom = " + this.firstname + ", nom = " + this.lastname
        + ", rang = "+ this.ranking.label + ", email = " + this.email + ", téléphone = " + this.cellPhone + /* ", diplômes = " + degrees + */" adresse = ]";
  }

  // GETTER/SETTER

  /**
   * @return the ranking
   */
  public RankingCandidate getRanking() {
    return this.ranking;
  }

  /**
   * @return the ranking label
   */
  public String getRankingLabel() {
    return this.ranking.label;
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
    return this.feedback;
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
    return this.degrees;
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
    return this.matters;
  }

  /**
   * @param matters the matters to set
   */
  public void setMatters(List<Matter> matters) {
    this.matters = matters;
  }

  public void addMatter(Matter matter) {
    if (!this.matters.contains(matter)) {
      this.matters.add(matter);
      if (matter.getCandidates().contains(this)) {
          matter.getCandidates().add(this);
      }
    }
  }

  /**
   * @return the sessions
   */
  public List<Session> getSessions() {
    return this.sessions;
  }

  /**
   * @param sessions the sessions to set
   */
  public void setSessions(List<Session> sessions) {
    this.sessions = sessions;
  }
  /**
   * @return the address
   */
  public Address getAddress() {
    return this.address;
  }

  /**
   * @param address the address to set
   */
  public void setAddress(Address address) {
    this.address = address;
//    address.getCandidates().add(this);
  }
}
