package com.apsidepoei.projetpoei.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.apsidepoei.projetpoei.database.contracts.CandidateContract;
import com.apsidepoei.projetpoei.database.contracts.SessionContract;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * This class is the session entity.
 *
 * @author benjamin-m
 *
 */
@Entity
@Table(name = SessionContract.TABLE)
@AttributeOverride(name = "id", column = @Column(name = SessionContract.COL_ID))
public class Session extends EntityDb {

  @JsonProperty(value = SessionContract.COL_NAME)
  @Column(name = SessionContract.COL_NAME, nullable = false, length = 50)
  private String name;

  @JsonProperty(value = SessionContract.COL_DATE_START)
  @Column(name = SessionContract.COL_DATE_START, nullable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @JsonProperty(value = SessionContract.COL_DATE_END)
  @Column(name = SessionContract.COL_DATE_END, nullable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;

  @JsonProperty(value = SessionContract.COL_CANDIDATES)
  @ManyToMany(targetEntity = Candidate.class)
  @JoinTable(name = "session_candidates", joinColumns = {
      @JoinColumn(name = SessionContract.COL_ID) }, inverseJoinColumns = {
          @JoinColumn(name = CandidateContract.COL_ID) })
  private List<Candidate> candidates;


  /**
   * Empty constructor.
   */
  public Session() {
    super();
    this.candidates = new ArrayList<Candidate>();
  }

  /**
   * Constructor for a new Session.
   *
   * @param name      = the name
   * @param startDate = the startDate
   * @param endDate   = the endDate
   */
  public Session(String name, LocalDate startDate, LocalDate endDate) {
    super();
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.candidates = new ArrayList<Candidate>();
  }

  /**
   * Constructor with id for a new Session.
   *
   * @param name      = the name
   * @param startDate = the startDate
   * @param endDate   = the endDate
   */
  public Session(String name, LocalDate startDate, LocalDate endDate, List<Candidate> candidates) {
    super();
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.candidates = candidates;
  }


  /**
   * Override toString() function.
   */
  @Override
  public String toString() {
    return "session [Id = " + getId() + ", name=" + name + ", startDate=" + startDate + ", endDate="
        + endDate + "]";
  }


  // GETTER/SETTER

  /**
   * The name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name.
   *
   * @param name = the name
   */
  public void setName(String name) {
    this.name = name;
  }



  /**
   * @return the startDate
   */
  public LocalDate getStartDate() {
    return startDate;
  }

  /**
   * @param startDate the startDate to set
   */
  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  /**
   * @return the endDate
   */
  public LocalDate getEndDate() {
    return endDate;
  }

  /**
   * @param endDate the endDate to set
   */
  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  /**
   * @return the candidates
   */
  public List<Candidate> getCandidates() {
    return candidates;
  }

  /**
   * @param candidates the candidates to set
   */
  public void setCandidates(List<Candidate> candidates) {
    this.candidates = candidates;
  }


}
