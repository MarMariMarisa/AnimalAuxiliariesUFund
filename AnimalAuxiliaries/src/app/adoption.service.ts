import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, of, tap } from 'rxjs';
import { Animal } from './animal';
@Injectable({
  providedIn: 'root',
})
export class AdoptionService {
  private basketUrl: string = 'http://localhost:8080/adoptioncupboard'; // URL to web api

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
  getAnimals(): Observable<Animal[]> {
    const url = `${this.basketUrl}`;
    return this.http.get<Animal[]>(url, this.httpOptions).pipe(
      tap((_) => this.log('fetched funded')),
      catchError(this.handleError('getFunded', []))
    );
  }

  constructor(private http: HttpClient) {}
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
    console.log(message);
  }
}
