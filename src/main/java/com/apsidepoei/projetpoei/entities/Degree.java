package com.apsidepoei.projetpoei.entities;

import static org.mockito.Mockito.RETURNS_SELF;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.apsidepoei.projetpoei.database.contracts.DegreeContract;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the Degree entity.
 *
 * @author thomas
 *
 */
@Entity
@Table(name = DegreeContract.TABLE)
@AttributeOverride(name = "id", column = @Column(name = DegreeContract.COL_ID))
public class Degree extends EntityDb {

  @JsonProperty(value = DegreeContract.COL_NAME)
  @Column(name = DegreeContract.COL_NAME, nullable = false)
  private String name;

  @JsonProperty(value = DegreeContract.COL_LEVEL)
  @Column(name = DegreeContract.COL_LEVEL, nullable = false, length = 50)
  private String level;

  @JsonProperty(value = DegreeContract.COL_CANDIDATES)
//  @ManyToMany(targetEntity = Candidate.class)
//  @JoinTable(name = "degree_candidate", joinColumns = {
//      @JoinColumn(name = DegreeContract.COL_ID) }, inverseJoinColumns = {
//          @JoinColumn(name = CandidateContract.COL_ID) })
  @ManyToMany(fetch = FetchType.LAZY,
  cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  },
  mappedBy = "degrees") // Degree sera esclave de la relation voir https://stackoverflow.com/a/14111651/8899653
  private final List<Candidate> candidates = new ArrayList<>();


  /**
   *
   */
  public Degree() {
    super();
  }

  /**
   * Constructor for a new degree.
   *
   * @param name  = the name
   * @param level = the level
   */
  public Degree(String name, String level) {
    super();
    this.name = name;
    this.level = level;
  }

  /**
   * @param name
   * @param level
   * @param candidates
   */
  public Degree(String name, String level, List<Candidate> candidates) {
    super();
    this.name = name;
    this.level = level;
    for (Candidate candidate : candidates) {
      this.addCandidate(candidate);
    }
  }


  /**
   * Override toString() function.
   */
  @Override
  public String toString() {
    return "Diplome [Id = " + this.getId() + ", nom=" + this.name + ", niveau=" + this.level + "]";
  }


  // GETTER/SETTER

  /**
   * The name.
   *
   * @return the name.
   */
  public String getName() {
    return this.name;
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
   * The level.
   *
   * @return the name.
   */
  public String getLevel() {
    return this.level;
  }

  /**
   * Set the level.
   *
   * @param level = the level
   */
  public void setLevel(String level) {
    this.level = level;
  }

  /**
   * Constructor with id for new degree.
   *
   * @param id    = the id
   * @param name  = the name
   * @param level = the level
   */
  public Degree(int id, String name, String level) {
    super();
    this.setId(id);
    this.name = name;
    this.level = level;
  }

  /**
   * @return the candidates
   */
  public List<Candidate> getCandidates() {
    return this.candidates;
  }

  /**
   * @param candidates the candidates to set
   */
//  public void setCandidates(List<Candidate> candidates) {
//    this.candidates = candidates;
//  }

  public void addCandidate(final Candidate candidate) {
    if (!this.candidates.contains(candidate)) {
      this.candidates.add(candidate);
      if (!candidate.getDegrees().contains(this)) {
        candidate.getDegrees().add(this);
      }
    }
  }

  public void removeCandidate(final Candidate candidate) {
    if (this.candidates.contains(candidate)) {
      this.candidates.remove(candidate);
      if (candidate.getDegrees().contains(this)) {
        candidate.getDegrees().remove(this);
      }
    }
  }

}
