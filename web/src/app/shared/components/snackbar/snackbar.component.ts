import { Component, Inject, OnInit } from '@angular/core';
import { MatSnackBarRef, MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';

@Component({
  selector: 'app-snackbar',
  templateUrl: './snackbar.component.html',
  styleUrls: ['./snackbar.component.scss']
})
export class SnackbarComponent implements OnInit {
  btnClose = true;
  classInfo = true;
  classWarning = false;
  classError = false;
  classSuccess = false;
  constructor(
    public snackBarRef: MatSnackBarRef<SnackbarComponent>,
    @Inject(MAT_SNACK_BAR_DATA) public data: { msg: string, snack: string, btnClose: boolean }
  ) {
    this.btnClose = this.data.btnClose;

    if (this.data.snack) {
      // document.getElementById('snack-bar').classList.add(this.data.snack);
      switch (this.data.snack) {
        case 'info':
          this.classInfo = true;
          this.classWarning = false;
          this.classError = false;
          this.classSuccess = false;
          break;
        case 'warning':
          this.classWarning = true;
          this.classInfo = false;
          this.classError = false;
          this.classSuccess = false;
          break;
        case 'error':
          this.classError = true;
          this.classWarning = false;
          this.classInfo = false;
          this.classSuccess = false;
          break;
        case 'success':
          this.classSuccess = true;
          this.classError = false;
          this.classWarning = false;
          this.classInfo = false;
          break;
        default:
          this.classInfo = true;
          this.classWarning = false;
          this.classError = false;
          this.classSuccess = false;
          break;
      }
    }
  }

  ngOnInit(): void {
  }

}
