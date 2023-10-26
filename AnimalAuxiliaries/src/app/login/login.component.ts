import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Helper } from '../helper';
import { FundingBasketService } from '../funding-basket.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  username: string = '';

  constructor(
    private router: Router,
    private auth: AuthService,
    private fundingBasketService: FundingBasketService
  ) {}

  login() {
    if (this.username === 'admin') {
      this.router.navigate(['/manager']);
    } else if (this.username == 'helperOne' || this.username == 'helperTwo') {
      this.auth.setUsername(this.username);
      this.router.navigate(['/helper']);
    } else {
      window.alert('Invalid login!');
    }
  }
  getUsername(): string {
    return this.username;
  }
  add(username: string): void {
    this.username = username;

    this.fundingBasketService
      .createHelper({
        id: 0,
        username: this.username,
        basket: [],
      } as Helper)
      .subscribe((res) => res);
  }
}
