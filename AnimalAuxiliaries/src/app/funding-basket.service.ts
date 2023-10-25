import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Observable, catchError, of, tap } from 'rxjs';
import { Need } from './need';

@Injectable({
  providedIn: 'root'
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

  getBasket(): Observable<Need[]> {
    return this.http.get<Need[]>(this.basketUrl).pipe(
      tap((_) => this.log('fetched basket')),
      catchError(this.handleError('getEntireCupboard', []))
    );
  }
  getNeed(id: string): Observable<Need> {
    const url = `${this.basketUrl}/${id}`; ///cupboard?id="
    return this.http.get<Need>(url).pipe(
      tap((_) => this.log(`fetched need id=${id}`)),
      catchError(this.handleError<Need>(`getNeed id=${id}`))
    );
  }
  deleteNeed(name: string): Observable<Need> {
    const url = `${this.basketUrl}/${name}`;
    return this.http.delete<Need>(url, this.httpOptions).pipe(
      tap((_) => this.log(`deleted need name=${name}`)),
      catchError(this.handleError<Need>('deleteNeed'))
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
