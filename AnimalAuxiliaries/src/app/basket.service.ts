import { ChangeDetectorRef, Injectable, NgZone } from '@angular/core';
import { AuthService } from './auth.service';
import { FundingBasketService } from './funding-basket.service';
import { CupboardService } from './cupboard.service';
import { Need } from './need';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BasketService {
  constructor(
    private cupboardService: CupboardService,
    private fundingbasketService: FundingBasketService,
    private auth: AuthService
  ) {}

  basket: Need[] = [];
  getBasket(): Observable<Need[]> {
    return this.fundingbasketService.getBasket(this.auth.getUsername());
  }
  addToBasket(need: Need): void {
    if (need.quantity == 0) return;
    need.quantity--;
    if (!need) return;
    this.fundingbasketService
      .addToBasket(this.auth.getUsername(), need)
      .subscribe((basket) => {
        this.basket = [...this.basket, basket];
      });
  }
  removeFromBasket(need: Need): number {
    let res: number = need.quantity;
    this.fundingbasketService
      .removeFromBasket(this.auth.getUsername(), need.id)
      .subscribe((aNeed) => {
        let i = this.basket.findIndex((need) => need.id == aNeed.id);
        if (i != -1) this.basket.splice(i, 1);
      });
    return res;
  }
}
