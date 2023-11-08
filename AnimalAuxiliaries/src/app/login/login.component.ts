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

  login(username: string, password: string) {
    this.username = username;
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

  async add(username: string, password: string): Promise<void> {
    this.username = username;
    this.fundingBasketService
      .createHelper({
        id: 0,
        password: password,
        username: this.username,
        basket: {
          needs: [],
        },
      } as Helper)
      .subscribe((res) => res);
  }
}
