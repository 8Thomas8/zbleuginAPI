/**
 *
 */
package com.apsidepoei.projetpoei.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.apsidepoei.projetpoei.database.contracts.AddressContract;
import com.apsidepoei.projetpoei.database.contracts.PersonContract;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vianney
 *
 */
@Entity
@Table(name = PersonContract.TABLE)
@AttributeOverride(name = "id", column = @Column(name = PersonContract.COL_ID))
public class Person extends EntityDb {

  @JsonProperty(value = PersonContract.COL_FIRSTNAME)
  @Column(name = PersonContract.COL_FIRSTNAME, nullable = false)
  private String firstname;

  @JsonProperty(value = PersonContract.COL_LASTNAME)
  @Column(name = PersonContract.COL_LASTNAME, nullable = false)
  private String lastname;

  @JsonProperty(value = PersonContract.COL_EMAIL)
  @Column(name = PersonContract.COL_EMAIL, nullable = false)
  private String email;

  @JsonProperty(value = PersonContract.COL_CELL_PHONE)
  @Column(name = PersonContract.COL_CELL_PHONE, nullable = false)
  private String cellPhone;

  @JsonProperty(value = PersonContract.COL_HOME_PHONE)
  @Column(name = PersonContract.COL_HOME_PHONE, nullable = false)
  private String homePhone;

  @JsonProperty(value = PersonContract.COL_COMMENTARY)
  @Column(name = PersonContract.COL_COMMENTARY, nullable = false)
  private String commentary;

  @JsonProperty(value = PersonContract.COL_FK_ID_ADDRESS)
  @ManyToOne(targetEntity=Address.class,optional=true)
  @JoinColumn(name=PersonContract.COL_FK_ID_ADDRESS, referencedColumnName=AddressContract.COL_ID)
  private Address address;

  /**
   * @return the firstname
   */
  public String getFirstname() {
    return firstname;
  }

  /**
   * @param firstname the firstname to set
   */
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  /**
   * @return the lastname
   */
  public String getLastname() {
    return lastname;
  }

  /**
   * @param lastname the lastname to set
   */
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the cellPhone
   */
  public String getCellPhone() {
    return cellPhone;
  }

  /**
   * @param cellPhone the cellPhone to set
   */
  public void setCellPhone(String cellPhone) {
    this.cellPhone = cellPhone;
  }

  /**
   * @return the homePhone
   */
  public String getHomePhone() {
    return homePhone;
  }

  /**
   * @param homePhone the homePhone to set
   */
  public void setHomePhone(String homePhone) {
    this.homePhone = homePhone;
  }

  /**
   * @return the commentary
   */
  public String getCommentary() {
    return commentary;
  }

  /**
   * @param commentary the commentary to set
   */
  public void setCommentary(String commentary) {
    this.commentary = commentary;
  }
}
