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
    const responseText = document.getElementById('response-text');
    this.username = username;
    if (this.username === 'admin') {
      if (password === 'admin') {
        this.auth.setUsername('admin');
        this.router.navigate(['/manager']);
      } else if (responseText) {
        responseText.textContent = 'Invalid Login!';
        responseText.style.color = '#c91d06';
      }
    } else {
      this.auth.setUsername(username);
      this.fundingBasketService
        .authenticate(username, password)
        .subscribe((res) => {
          if (res) {
            console.log(this.auth.getUsername());
            this.router.navigate(['/helper']);
          } else {
            if (responseText) {
              responseText.textContent = 'Invalid Login!';
              responseText.style.color = '#c91d06';
            }
          }
        });
    }
  }
  getUsername(): string {
    return this.username;
  }

  async add(username: string, password: string): Promise<void> {
    this.username = username;
    const responseText = document.getElementById('response-text');
    this.fundingBasketService
      .createHelper({
        id: 0,
        password: password,
        username: this.username,
        basket: {
          needs: [],
        },
      } as Helper)
      .subscribe((res) => {
        if (res == undefined) {
          if (responseText) {
            responseText.textContent = 'Username taken';
            responseText.style.color = '#c91d06';
          }
        } else {
          // if (responseText) {
          //   responseText.textContent = 'Account created successfully';
          //   responseText.style.color = '#1ba802';
          // }
          this.login(username, password);
        }
      });
  }
}
