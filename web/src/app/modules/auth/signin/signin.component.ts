import { catchError, tap } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { TokenResponse } from 'src/app/models/TokenResponse';
import { UserCredentials } from 'src/app/models/UserCredentials';
import { AuthService } from '../services/auth.service';

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
    private authService: AuthService
  ) { }

  ngOnInit(): void {
  }

  authenticate() {
    const credentials = new UserCredentials();
    credentials.username = this.loginForm.value.username;
    credentials.password = this.loginForm.value.password;
    this.authService.authenticate(credentials)
      .pipe(
        tap((userCredentials: TokenResponse) => {
          if(userCredentials.access_token !== null || userCredentials.access_token !== undefined) {
            this.authService.userIsAuthenticated.emit(true);
          }
        }),
      )
      .subscribe({
        next(resposta: TokenResponse) {
          console.log('Token gerado ', resposta);
        },
        error(error: HttpErrorResponse) {
          console.log('Erro gerado ao realizar login ', error.message)
        },
        complete() {
          console.log('Usuário autenticado com sucesso!');
        }
      });
  }
}
