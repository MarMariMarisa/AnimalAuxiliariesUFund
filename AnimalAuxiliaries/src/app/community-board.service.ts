import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Observable, catchError, of, tap } from 'rxjs';
import { Post } from './post';
@Injectable({
  providedIn: 'root'
})
export class CommunityBoardService {

  private communityBoardUrl = 'http://localhost:8080/communityboard'; // URL to web api

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

  getCommunityBoard(): Observable<Post[]> {
    return this.http.get<Post[]>(this.communityBoardUrl,this.httpOptions).pipe(
      tap((_) => this.log('fetched cupboard')),
      catchError(this.handleError('getEntireCupboard', []))
    );
  }
  getPost(id: string): Observable<Post> {
    const url = `${this.communityBoardUrl}/${id}`; ///cupboard?id="
    return this.http.get<Post>(url,this.httpOptions).pipe(
      tap((_) => this.log(`fetched need id=${id}`)),
      catchError(this.handleError<Post>(`getNeed id=${id}`))
    );
  }
  createPost(post: Post): Observable<Post> {
    return this.http.post<Post>(this.communityBoardUrl, post, this.httpOptions).pipe(
      tap((newPost: Post) => this.log(`added need w/ id=${newPost.id}`)),
      catchError(this.handleError<Post>('addPost'))
    );
  }
  deletePost(name: string): Observable<Post> {
    const url = `${this.communityBoardUrl}/${name}`;
    return this.http.delete<Post>(url, this.httpOptions).pipe(
      tap((_) => this.log(`deleted need name=${name}`)),
      catchError(this.handleError<Post>('deletePost'))
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

  /** Log a CupboardService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`CupboardService: ${message}`);
  }

 

}
