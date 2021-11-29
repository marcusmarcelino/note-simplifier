package br.com.apinotesimplifier.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
