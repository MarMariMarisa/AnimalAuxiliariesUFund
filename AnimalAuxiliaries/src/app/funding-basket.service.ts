import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Observable, catchError, of, tap } from 'rxjs';
import { Need } from './need';
import { fundingBasket } from './funding-basket';

@Injectable({
  providedIn: 'root',
})
export class FundingBasketService {
  private basketUrl = 'http://localhost:8080/funding-basket'; // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) {}

  getBasket(username: String): Observable<Need[]> {
    const url = `${this.basketUrl}/${username}/`;
    return this.http.get<Need[]>(url).pipe(
      tap((_) => this.log('fetched basket')),
      catchError(this.handleError('getEntireCupboard', []))
    );
  }
  addToBasket(id: string, username: string): Observable<Need> {
    const url = `${this.basketUrl}/${username}/${id}`;
    return this.http.post<Need>(url, this.httpOptions).pipe(
      tap((_) => this.log(`added need w/ id=${id}`)),
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

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`HeroService: ${message}`);
  }
}
