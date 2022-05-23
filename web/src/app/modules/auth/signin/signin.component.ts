import { MatDialog } from '@angular/material/dialog';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { TokenResponse } from 'src/app/models/TokenResponse';
import { UserCredentials } from 'src/app/models/UserCredentials';
import { AuthService } from '../services/auth.service';
import { ModalStandbyComponent } from 'src/app/shared/components/modal-standby/modal-standby.component';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {
  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  constructor(
    private authService: AuthService,
    private router: Router,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
  }

  authenticate() {
    const credentials = new UserCredentials();
    credentials.username = this.loginForm.value.username;
    credentials.password = this.loginForm.value.password;
    const dialogRef = this.dialog.open(ModalStandbyComponent, {
      width: "500px",
      height: "500px",
      panelClass: 'custom-dialog'
    });
    this.authService.authenticate(credentials)
      .subscribe({
        next: (userCredentials: TokenResponse) => {
          // console.log('Token gerado ', userCredentials);
          if(userCredentials.access_token !== null || userCredentials.access_token !== undefined) {
            this.authService.setToken(userCredentials.access_token);
            this.authService.setUserIsAuthenticated(true);
            this.router.navigate(['/home']);
            dialogRef.close();
          }
        },
        error: (error: HttpErrorResponse) => {
          console.log('Erro gerado ao realizar login ', error.message);
          this.authService.setUserIsAuthenticated(false);
          dialogRef.close();
        },
        complete: () => { /*console.log('Usuário autenticado com sucesso!');*/ }
      });
  }
}
