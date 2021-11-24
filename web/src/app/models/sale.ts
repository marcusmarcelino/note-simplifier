export class Sale {
  id: number | undefined;
  seller: string;
  amount: number;

  constructor() {
    this.id = undefined;
    this.seller = '';
    this.amount = 0;
  }
}
