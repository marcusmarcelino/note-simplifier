package br.com.apinotesimplifier.models;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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

  @OneToOne
  @JoinColumn(name = "id_user", referencedColumnName = "id")
  private User idUser;

  @ElementCollection
  @CollectionTable(name = "personal_data_address", joinColumns = @JoinColumn(name = "personal_data_id"))
  private List<Address> addresses;
}
