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
export class FundedService {
  private basketUrl: string = 'http://localhost:8080/cupboard/funded'; // URL to web api

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
  getFunded(): Observable<Need[]> {
    const url = `${this.basketUrl}`;
    return this.http.get<Need[]>(url, this.httpOptions).pipe(
      tap((_) => this.log('fetched funded')),
      catchError(this.handleError('getFunded', []))
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
    this.messageService.add(`FundedService: ${message}`);
  }
}
