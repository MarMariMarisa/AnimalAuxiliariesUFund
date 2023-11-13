import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Observable, catchError, of, tap } from 'rxjs';
import { Need } from './need';
import { fundingBasket } from './funding-basket';
import { Helper } from './helper';

@Injectable({
  providedIn: 'root',
})
export class FundingBasketService {
  private basketUrl: string = 'http://localhost:8080/funding-basket'; // URL to web api

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Credentials': 'true',
      'Access-Control-Allow-Headers':
        'X-Requested-With, content-type, Authorization',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,OPTIONS,DELETE',
    }),
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) {}
  authenticate(username: string, password: string) {
    const url = `${this.basketUrl}/${username}/${password}`;
    return this.http.get<Boolean>(url, this.httpOptions).pipe(
      tap((_) => this.log('authenicated user')),
      catchError(this.handleError('authenicate', []))
    );
  }
  getBasket(username: string): Observable<Need[]> {
    const url = `${this.basketUrl}/${username}`;

    return this.http.get<Need[]>(url, this.httpOptions).pipe(
      tap((_) => this.log('fetched basket')),
      catchError(this.handleError('getBasket', []))
    );
  }

  addToBasket(username: string, need: Need): Observable<Need> {
    const url = `${this.basketUrl}/${username}`;

    return this.http
      .post<Need>(url, JSON.stringify(need), this.httpOptions)
      .pipe(
        tap((_) => this.log(`added need =${need}`)),
        catchError(this.handleError<Need>('addToBasket'))
      );
  }

  removeFromBasket(username: string, id: String): Observable<Need> {
    const url = `${this.basketUrl}/${username}/${id}`;
    return this.http.delete<Need>(url, this.httpOptions).pipe(
      tap((_) => this.log(`deleted need id=${id}`)),
      catchError(this.handleError<Need>('deleteNeedFromBasket'))
    );
  }
  createHelper(helper: Helper): Observable<Helper> {
    return this.http
      .post<Helper>(this.basketUrl, helper, this.httpOptions)
      .pipe(
        tap((newHelper: Helper) =>
          this.log(`added need w/ id=${newHelper.id}`)
        ),
        catchError(this.handleError<Helper>('addHelper'))
      );
  }
  checkout(username: string) {
    const url = `${this.basketUrl}/${username}`;
    return this.http.delete<Boolean>(url, this.httpOptions).pipe(
      tap((_) => this.log(`deleted checked out user = ${username}`)),
      catchError(this.handleError<Boolean>('deleteNeedFromBasket'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
  private log(message: string) {
    this.messageService.add(`HeroService: ${message}`);
  }
}
