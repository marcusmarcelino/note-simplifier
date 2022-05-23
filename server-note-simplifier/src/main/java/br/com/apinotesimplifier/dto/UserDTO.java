package br.com.apinotesimplifier.dto;

import br.com.apinotesimplifier.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
  private Long id;
  private String username;
  private String profission;
  private String accountStatus;
  private Long idpersonalData;

  public UserDTO() {
  }

  public UserDTO(
      Long id,
      String username,
      String profission,
      String accountStatus,
      Long idpersonalData) {
    this.id = id;
    this.username = username;
    this.profission = profission;
    this.accountStatus = accountStatus;
    this.idpersonalData = idpersonalData;
  }

  public UserDTO(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.profission = user.getProfession();
    this.accountStatus = user.getAccountStatus();
    this.idpersonalData = user.getIdPersonalData().getId();
  }
}
