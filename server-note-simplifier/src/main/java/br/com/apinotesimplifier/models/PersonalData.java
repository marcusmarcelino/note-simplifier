package br.com.apinotesimplifier.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "personal_data")
public class PersonalData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "contact")
  private String contact;
  @Column(name = "district")
  private String district;
  @Column(name = "road")
  private String road;
  @Column(name = "number")
  private String number;
  @Column(name = "complement")
  private String complement; 
  @Column(name = "city")
  private String city;
  @Column(name = "uf")
  private String uf;

  @JoinColumn(name = "id_user",  referencedColumnName = "id", nullable = true, insertable = true, updatable = true)
  @OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private User user;
}
