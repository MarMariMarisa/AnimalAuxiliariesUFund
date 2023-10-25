import { Component } from '@angular/core';
import { Need } from '../need';
import { FundingBasketService } from '../funding-basket.service';
import { LoginComponent } from '../login/login.component';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-funding-basket',
  templateUrl: './funding-basket.component.html',
  styleUrls: ['./funding-basket.component.css'],
})
export class FundingBasketComponent {
  constructor(
    private fundingBasketService: FundingBasketService,
    private auth: AuthService
  ) {}
  basket: Need[] = [];
  ngOnInit(): void {
    this.getBasket();
  }
  getBasket(): void {
    this.fundingBasketService
      .getBasket(this.auth.getUsername())
      .subscribe((need) => (this.basket = need));
  }
}
