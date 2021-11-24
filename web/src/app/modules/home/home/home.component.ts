import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarRef } from '@angular/material/snack-bar';
import { catchError, Observable, of } from 'rxjs';
import { Sale } from 'src/app/models/sale';
import { SnackbarComponent } from 'src/app/shared/components/snackbar/snackbar.component';

import { HomeService } from '../services/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  displayedColumns: string[] = ['id', 'seller', 'amount'];
  sales: Observable<Sale[]>;

  constructor(
    private homeService: HomeService,
    private snackBar: MatSnackBar
  ) {
    this.sales = this.homeService.findLastSales()
      .pipe(
        catchError((error: HttpErrorResponse) => {
          console.log('Erro ao listar ultimas vendas', error);
          this.snackBarMsgAlert(3000, `Erro ao listar ultimas vendas - ${error.message}`, 'error');
          return of([]);
        })
      );
  }

  ngOnInit(): void {
  }

  snackBarMsgAlert(
    duration: number,
    msg: string,
    snack: string,
    verticalPos?: 'top' | 'bottom',
    horizontalPos?: 'start' | 'center' | 'end' | 'left' | 'right'
  ): MatSnackBarRef<SnackbarComponent> {
    return this.snackBar.openFromComponent(SnackbarComponent, {
      duration,
      data: {
        msg,
        snack,
        btnClose: true
      },
      verticalPosition: verticalPos || 'bottom',
      horizontalPosition: horizontalPos || 'center',
      panelClass: `snack-${snack}`
    });
  }
}
