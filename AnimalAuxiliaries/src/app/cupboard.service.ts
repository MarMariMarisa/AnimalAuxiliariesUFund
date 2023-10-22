import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Cupboard } from './cupboard';
import { MessageService } from './message.service';


@Injectable({ providedIn: 'root' })
export class CupboardService {

  private cupboardUrl = 'http://localhost:8080/cupboard';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET heroes from the server */
  getEntireCupboard(){
    return this.http.get(this.cupboardUrl)
      .pipe(
        tap(_ => this.log('fetched cupboard')),
        catchError(this.handleError('getEntireCupboard'))
      );
  }
  getRetiredNeeds(){
    
    const url = `${this.cupboardUrl}/retired`;
    return this.http.get(url)
      .pipe(
        tap(_ => this.log('fetched cupboard')),
        catchError(this.handleError('getEntireCupboard'))
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
