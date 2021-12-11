import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
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
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  authenticate() {
    const credentials = new UserCredentials();
    credentials.username = this.loginForm.value.username;
    credentials.password = this.loginForm.value.password;
    this.authService.authenticate(credentials)
      .subscribe({
        next: (userCredentials: TokenResponse) => {
          console.log('Token gerado ', userCredentials);
          if(userCredentials.access_token !== null || userCredentials.access_token !== undefined) {
            this.authService.setUserIsAuthenticated(true);
            this.router.navigate(['/home']);
          }
        },
        error: (error: HttpErrorResponse) => {
          console.log('Erro gerado ao realizar login ', error.message);
          this.authService.setUserIsAuthenticated(false);
        },
        complete: () => {
          console.log('Usuário autenticado com sucesso!');
        }
      });
  }
}
