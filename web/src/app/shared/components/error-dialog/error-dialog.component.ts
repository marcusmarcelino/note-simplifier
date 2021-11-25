import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface DialogData {
  title: string | '';
  message: string | '';
  btnCloseInHeader: boolean | true;
  btnCloseInFooter: boolean | false;
}

@Component({
  selector: 'app-error-dialog',
  templateUrl: './error-dialog.component.html',
  styleUrls: ['./error-dialog.component.scss']
})
export class ErrorDialogComponent implements OnInit {
  title: string = '';
  message: string = '';
  btnCloseInHeader: boolean = true;
  btnCloseInFooter: boolean = false;

  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: DialogData,
    private dialogRef: MatDialogRef<ErrorDialogComponent>
  ) {
    if(this.data) {
      this.title = this.data.title;
      this.message = this.data.message;
      this.btnCloseInHeader = this.data.btnCloseInHeader;
      this.btnCloseInFooter = this.data.btnCloseInFooter;
    }
  }

  ngOnInit(): void {
  }

  close() {
    this.dialogRef.close();
  }
}
