import { PersonalData } from "./PersonalData";
import { Role } from "./Role";

export class User {
  id: number | undefined;
  username: string;
  password: string;
  roles: Role[];
  accountStatus: 'expired' | 'active' | 'inactive ' | 'blocked';
  idPersonalData: PersonalData | undefined;

  constructor() {
    this.id = undefined;
    this.username = '';
    this.password = '';
    this.roles = [];
    this.accountStatus = 'active';
  }
}
