import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Sale } from 'src/app/models/sale';
import { delay, Observable, take, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  private readonly api: string = '../../../../assets/mock.json';

  constructor(
    private httpClient: HttpClient,
  ) { }

  findLastSales(): Observable<Sale[]> {
    return this.httpClient.get<Sale[]>(this.api,  {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8',
        Authorization: 'Bearer ' + 'this.requisicao.getToken()'
      })
    })
      .pipe(
        take(1), // Isso faz com que eu encerre o acesso após receber as informaçõs
        // first(), // Ou posso colocar colocar que cancela assim que eu receber a primeira resposta vinda do servidor
        delay(2000),
        tap(sales => console.log(sales))
      );
  }
}
