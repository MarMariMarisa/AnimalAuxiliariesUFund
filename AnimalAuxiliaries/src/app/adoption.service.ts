import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, of, tap } from 'rxjs';
import { Animal } from './animal';
@Injectable({
  providedIn: 'root',
})
export class AdoptionService {
  private adoptionCupboardUrl: string = 'http://localhost:8080/adoptioncupboard'; // URL to web api

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
    const url = `${this.adoptionCupboardUrl}`;
    return this.http.get<Animal[]>(url, this.httpOptions).pipe(
      tap((_) => this.log('fetched funded')),
      catchError(this.handleError('getFunded', []))
    );
  }
  adopt(id: string): Observable<Boolean> {
    const url = `http://localhost:8080/funding-basket/${id}`;
    return this.http.put<boolean>(url, this.httpOptions).pipe(
      tap((_) => this.log('fetched funded')),
      catchError(this.handleError<boolean>('getFunded'))
    );
  }

  getAnimal(id: string): Observable<Animal> {
    const url = `${this.adoptionCupboardUrl}/${id}`; 
    return this.http.get<Animal>(url,this.httpOptions).pipe(
      tap((_) => this.log(`fetched animal id=${id}`)),
      catchError(this.handleError<Animal>(`getAnimal id=${id}`))
    );
  }
  createAnimal(animal: Animal): Observable<Animal> {
    return this.http.post<Animal>(this.adoptionCupboardUrl, animal, this.httpOptions).pipe(
      tap((newAnimal: Animal) => this.log(`added need w/ id=${newAnimal.id}`)),
      catchError(this.handleError<Animal>('addAnimal'))
    );
  }
  deleteAnimal(id: string): Observable<Animal> {
    const url = `${this.adoptionCupboardUrl}/${id}`;
    return this.http.delete<Animal>(url, this.httpOptions).pipe(
      tap((_) => this.log(`deleted post id=${id}`)),
      catchError(this.handleError<Animal>('deleteAnimal'))
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
