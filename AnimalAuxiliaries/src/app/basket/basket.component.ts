import { Component } from '@angular/core';
import { Need } from '../need';
import { AuthService } from '../auth.service';
import { FundingBasketService } from '../funding-basket.service';
import { BasketService } from '../basket.service';
import { CupboardService } from '../cupboard.service';
import { Router } from '@angular/router';

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
    private cupboardService: CupboardService,
    private router: Router
  ) {}
  basket: Need[] = [];
  currentNeeds: Need[] = [];
  sum: number = 0;
  ngOnInit(): void {
    if (this.auth.getUsername() == '') this.router.navigate(['/login']);
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
    const cost = document.getElementById('totalCost');

    setTimeout(() => {
      this.basket.forEach((need) => {
        this.sum += need.price * need.quantity;
      });
      if (cost) cost.innerText = `Total: $${this.sum}`;
    }, 60);
    const surp = <HTMLInputElement>document.getElementById('surplus');
    if (surp)
      surp.addEventListener('change', () => {
        if (cost && parseFloat(surp.value) >= 0.01) {
          const aSum: string = (
            this.sum + parseFloat(parseFloat(surp.value).toFixed(2))
          ).toFixed(2);
          cost.innerText = `Total: $${aSum}`;
        } else {
          if (cost) cost.innerText = `Total: $${this.sum}`;
        }
      });
  }

  getEntireCupboard(): void {
    this.cupboardService
      .getEntireCupboard()
      .subscribe((need) => (this.currentNeeds = [...need]));
  }
  removeFromBasket = async (need: Need) => {
    let count: number = this.basketService.removeFromBasket(need);
    setTimeout(() => {
      this.fundingbasketService
        .getBasket(this.auth.getUsername())
        .subscribe((basket) => (this.basket = basket));
    }, 30);
    setTimeout(() => {
      if (this.basket.length == 0) {
        let container = document.getElementById('basketContainer');
        if (container)
          container.innerHTML =
            '<h2 style="padding:0.5rem 1rem 0.5rem 1rem">Your basket is empty!</h2>';
      }
    }, 50);
    const cost = document.getElementById('totalCost');
    setTimeout(() => {
      this.basket.forEach((need) => {
        this.sum = 0;
        this.sum += need.price * need.quantity;
      });
      if (cost) cost.innerText = `Total: $${this.sum}`;
      const surp = <HTMLInputElement>document.getElementById('surplus');
      if (surp)
        if (cost && parseFloat(surp.value) >= 0.01) {
          const aSum: string = (
            this.sum + parseFloat(parseFloat(surp.value).toFixed(2))
          ).toFixed(2);
          cost.innerText = `Total: $${aSum}`;
        } else {
          if (cost) cost.innerText = `Total: $${this.sum}`;
        }
    }, 60);
  };
  checkout(): void {
    this.fundingbasketService
      .checkout(this.auth.getUsername())
      .subscribe((res) => res);
    const surp = <HTMLInputElement>document.getElementById('surplus');
    if (surp) {
      this.cupboardService
        .addToSurplus(parseFloat(parseFloat(surp.value).toFixed(2)))
        .subscribe((res) => res);
    }
    let container = document.getElementById('basketContainer');
    if (container)
      container.innerHTML =
        '<h2 style="color:green;padding:0.5rem 1rem 0.5rem 1rem">Thank you for your contributions!</h2>';
    setTimeout(() => {
      if (container)
        container.innerHTML =
          '<h2 style="padding:0.5rem 1rem 0.5rem 1rem">Your basket is empty!</h2>';
    }, 1000);
  }
  increment(need: Need): void {
    this.cupboardService
      .getEntireCupboard()
      .subscribe((need) => (this.currentNeeds = [...need]));
    for (let x = 0; x < this.currentNeeds.length; x++) {
      if (this.currentNeeds[x].id == need.id) {
        this.basketService.addToBasket(this.currentNeeds[x]);
        break;
      }
    }
    setTimeout(() => {
      this.fundingbasketService
        .getBasket(this.auth.getUsername())
        .subscribe((basket) => (this.basket = basket));
    }, 30);
  }
  decrement(need: Need): void {
    this.basketService.decrementBasket(need);
    setTimeout(() => {
      this.fundingbasketService
        .getBasket(this.auth.getUsername())
        .subscribe((basket) => (this.basket = basket));
    }, 30);
    setTimeout(() => {
      this.fundingbasketService
        .getBasket(this.auth.getUsername())
        .subscribe((basket) => (this.basket = basket));
    }, 30);
    setTimeout(() => {
      if (this.basket.length == 0) {
        let container = document.getElementById('basketContainer');
        if (container)
          container.innerHTML =
            '<h2 style="padding:0.5rem 1rem 0.5rem 1rem">Your basket is empty!</h2>';
      }
    }, 50);
  }
}
