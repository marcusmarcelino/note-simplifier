package br.com.apinotesimplifier.dto;

import br.com.apinotesimplifier.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataDTO {
  private Long id;
  private String username;
  private String profission;
  private String accountStatus;
  private PersonalDataDTO idPersonalData;

  public UserDataDTO(
      Long id,
      String username,
      String profission,
      String accountStatus,
      PersonalDataDTO idPersonalData) {
    this.id = id;
    this.username = username;
    this.profission = profission;
    this.accountStatus = accountStatus;
    this.idPersonalData = idPersonalData;
  }

  public UserDataDTO(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.profission = user.getProfession();
    this.accountStatus = user.getAccountStatus().toString();
    this.idPersonalData = new PersonalDataDTO(user.getIdPersonalData());
  }
}
