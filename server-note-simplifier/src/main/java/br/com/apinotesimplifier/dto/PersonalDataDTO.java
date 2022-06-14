package br.com.apinotesimplifier.dto;

import java.util.List;

import br.com.apinotesimplifier.models.Address;
import br.com.apinotesimplifier.models.PersonalData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalDataDTO {
  private Long id;
  private String name;
  private String email;
  private String contact;
  private List<Address> addresses;

  public PersonalDataDTO(
      Long id,
      String name,
      String email,
      String contact,
      List<Address> addresses) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.contact = contact;
    this.addresses = addresses;
  }

  public PersonalDataDTO(PersonalData data) {
    this.id = data.getId();
    this.name = data.getName();
    this.email = data.getEmail();
    this.contact = data.getContact();
    this.addresses = data.getAddresses();
  }
}
