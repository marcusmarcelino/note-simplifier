export class PersonalData {
  private id: number | undefined;
  private name: string;
  private contact: string;
  private district: string;
  private road: string;
  private number: string;
  private complement: string;
  private city: string;
  private uf: string;


  constructor() {
    this.name = '';
    this.contact = '';
    this.district = '';
    this.road = '';
    this.number = '';
    this.complement = '';
    this.city = '';
    this.uf = '';
  }
}
