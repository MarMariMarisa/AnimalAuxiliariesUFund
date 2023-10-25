import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor() {}
  private username: string = '';
  setUsername(username: string) {
    this.username = username;
  }
  getUsername(): string {
    return this.username;
  }
}
