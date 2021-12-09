import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserCredentials } from 'src/app/models/UserCredentials';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {
  loginForm = new FormGroup({
    // userCredentials: new FormControl(UserCredentials),
    username: new FormControl(''),
    password: new FormControl('')
  });

  constructor(
    private authService: AuthService
  ) { }

  ngOnInit(): void {
  }

  authenticate() {
    console.log('Enviar dados ao servidor - ', this.loginForm.value);
    this.authService.authenticate(this.loginForm.value)
      .subscribe((resposta) => {
        console.log(resposta);
      });
  }
}
