import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  username: string = '';

  constructor(private router: Router, private auth: AuthService) {}

  login() {
    if (this.username === 'admin') {
      this.router.navigate(['/manager']);
    } else {
      this.auth.setUsername(this.username);
      this.router.navigate(['/helper']);
    }
  }
  getUsername(): string {
    return this.username;
  }
}
