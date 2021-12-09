import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable, EventEmitter } from '@angular/core';
import { first, Observable, tap } from 'rxjs';
import { UserCredentials } from 'src/app/models/UserCredentials';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  api = 'http://localhost:8081/api/login';

  userIsAuthenticated = new EventEmitter<boolean>();

  constructor(private httpClient: HttpClient) {}

  authenticate(credentials: UserCredentials): Observable<any> {
    const body = new HttpParams();
    body.set('username', credentials.username);
    body.set('password', credentials.password);
    return this.httpClient.post<any>(this.api, credentials, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8',
        Authorization: 'Bearer ' + '',
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
      })
    })
    .pipe(
      first(),
      tap(userCredentials => console.log('Authenticate service ', userCredentials))
    );
  }
}
