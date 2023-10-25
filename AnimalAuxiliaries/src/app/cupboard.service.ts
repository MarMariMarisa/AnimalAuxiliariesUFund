import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Cupboard } from './cupboard';
import { MessageService } from './message.service';

import { Need } from './need';
@Injectable({ providedIn: 'root' })
export class CupboardService {
  private cupboardUrl = 'http://localhost:8080/cupboard'; // URL to web api

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Credentials': 'true',
      "Access-Control-Allow-Headers": "X-Requested-With, content-type, Authorization",
      'Access-Control-Allow-Methods': 'GET,PUT,POST,OPTIONS,DELETE'})
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) {}

  getEntireCupboard(): Observable<Need[]> {
    return this.http.get<Need[]>(this.cupboardUrl,this.httpOptions).pipe(
      tap((_) => this.log('fetched cupboard')),
      catchError(this.handleError('getEntireCupboard', []))
    );
  }
  getNeed(id: string): Observable<Need> {
    const url = `${this.cupboardUrl}/${id}`; ///cupboard?id="
    return this.http.get<Need>(url,this.httpOptions).pipe(
      tap((_) => this.log(`fetched need id=${id}`)),
      catchError(this.handleError<Need>(`getNeed id=${id}`))
    );
  }
  createNeed(need: Need): Observable<Need> {
    return this.http.post<Need>(this.cupboardUrl, need, this.httpOptions).pipe(
      tap((newNeed: Need) => this.log(`added need w/ id=${newNeed.id}`)),
      catchError(this.handleError<Need>('addNeed'))
    );
  }
  deleteNeed(name: string): Observable<Need> {
    const url = `${this.cupboardUrl}/${name}`;
    return this.http.delete<Need>(url, this.httpOptions).pipe(
      tap((_) => this.log(`deleted need name=${name}`)),
      catchError(this.handleError<Need>('deleteNeed'))
    );
  }
  updateNeed(need: Need): Observable<any> {
    return this.http.put(this.cupboardUrl, need, this.httpOptions).pipe(
      tap((_) => this.log(`updated need id=${need.id}`)),
      catchError(this.handleError<any>('updateNeed'))
    );
  }
  searchNeeds(term: string): Observable<Need[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<Need[]>(`${this.cupboardUrl}/?name=${term}`,this.httpOptions).pipe(
      tap((x) =>
        x.length
          ? this.log(`found heroes matching "${term}"`)
          : this.log(`no heroes matching "${term}"`)
      ),
      catchError(this.handleError<Need[]>('searchHeroes', []))
    );
  }

  getRetiredNeeds() {
    const url = `${this.cupboardUrl}/retired`;
    return this.http.get(url,this.httpOptions).pipe(
      tap((_) => this.log('fetched cupboard')),
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
