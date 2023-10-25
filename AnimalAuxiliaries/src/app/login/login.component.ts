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

  constructor(private router: Router, private auth: AuthService,private fundingBasketService: FundingBasketService) {}

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
  add(username: String): void {
    if (!username) {
      return;
    }
    let a = JSON.parse(
      JSON.stringify({
        id: 0,
        username: username,
        basket: []
       
      } as Helper)
    );
    console.log(a);
    this.fundingBasketService.createHelper(a);
  }
}
