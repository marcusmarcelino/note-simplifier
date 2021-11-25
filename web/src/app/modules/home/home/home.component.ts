import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarRef } from '@angular/material/snack-bar';
import { catchError, Observable, of, tap } from 'rxjs';
import { Sale } from 'src/app/models/sale';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
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
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {
    this.sales = this.homeService.findLastSales()
      .pipe(
        tap(sales => this.snackBarMsgAlert(3000, `Ultimas vendas localizadas`, 'success')),
        catchError((error: HttpErrorResponse) => {
          if(error.status <= 400) {
            this.snackBarMsgAlert(3000, `Erro ${error.status}, não há vendas`, 'error');
          } else {
            this.modalAlert(error.status, error.message);
          }
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

  modalAlert(status: number, message: string) {
    this.dialog.open(ErrorDialogComponent, {
      width: '400px',
      data: {
        title: 'Atenção',
        message: `Erro ${status}. Ao buscar ultimas vendas - ${message}`,
        btnCloseInHeader: true
      }
    })
  }
}
