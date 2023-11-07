import { Component } from '@angular/core';
import { Need } from '../need';
import { AuthService } from '../auth.service';
import { FundingBasketService } from '../funding-basket.service';
import { BasketService } from '../basket.service';
import { CupboardService } from '../cupboard.service';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css'],
})
export class BasketComponent {
  constructor(
    private fundingbasketService: FundingBasketService,
    private auth: AuthService,
    private basketService: BasketService,
    private cupboardService: CupboardService
  ) {}
  ngOnInit(): void {
    this.getEntireCupboard();
    this.basketService
      .getBasket()
      .subscribe((basket) => (this.basket = basket));
    setTimeout(() => {
      if (this.basket.length == 0) {
        let container = document.getElementById('basketContainer');
        if (container)
          container.innerHTML =
            '<h2 style="padding:0.5rem 1rem 0.5rem 1rem">Your basket is empty!</h2>';
      }
    }, 50);
  }
  basket: Need[] = [];
  currentNeeds: Need[] = [];
  getEntireCupboard(): void {
    this.cupboardService
      .getEntireCupboard()
      .subscribe((need) => (this.currentNeeds = [...need]));
  }
  removeFromBasket = async (need: Need) => {
    let count: number = this.basketService.removeFromBasket(need);
    this.currentNeeds.forEach((aNeed) => {
      if (aNeed.id == need.id) {
        aNeed.quantity += count;
      }
    });

    setTimeout(() => {
      this.fundingbasketService
        .getBasket(this.auth.getUsername())
        .subscribe((basket) => (this.basket = basket));
    }, 30);
  };
}
