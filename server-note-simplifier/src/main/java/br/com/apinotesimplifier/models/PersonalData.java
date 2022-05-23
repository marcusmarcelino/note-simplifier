package br.com.apinotesimplifier.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "personal_data")
public class PersonalData {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, name = "id", nullable = false)
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "email", unique = true)
  private String email;
  @Column(name = "contact", unique = true)
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
