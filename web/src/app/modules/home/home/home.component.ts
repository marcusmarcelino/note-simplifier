import { Component, OnInit } from '@angular/core';
import { Sale } from 'src/app/models/sale';

const ELEMENT_DATA: Sale[] = [
  {id: 1, seller: 'Hydrogen', amount: 1.0079},
  {id: 2, seller: 'Helium', amount: 4.0026},
  {id: 3, seller: 'Lithium', amount: 6.941},
  {id: 4, seller: 'Beryllium', amount: 9.0122},
  {id: 5, seller: 'Boron', amount: 10.811},
  {id: 6, seller: 'Carbon', amount: 12.0107},
  {id: 7, seller: 'Nitrogen', amount: 14.0067},
  {id: 8, seller: 'Oxygen', amount: 15.9994},
  {id: 9, seller: 'Fluorine', amount: 18.9984},
  {id: 10, seller: 'Neon', amount: 20.1797},
];

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  displayedColumns: string[] = ['id', 'seller', 'amount'];
  sales = ELEMENT_DATA;
  constructor() { }

  ngOnInit(): void {
  }

}
