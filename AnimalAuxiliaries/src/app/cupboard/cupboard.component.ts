import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  OnInit,
} from '@angular/core';
import { Cupboard } from '../cupboard';
import { CupboardService } from '../cupboard.service';
import { Need } from '../need';
import { Observable, Subject } from 'rxjs';
import { FundingBasketService } from '../funding-basket.service';
import { AuthService } from '../auth.service';
import { FundingBasketComponent } from '../funding-basket/funding-basket.component';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { BasketService } from '../basket.service';
@Component({
  selector: 'app-cupboard',
  templateUrl: './cupboard.component.html',
  styleUrls: ['./cupboard.component.css'],
  changeDetection: ChangeDetectionStrategy.Default,
})
export class CupboardComponent implements OnInit {
  constructor(
    private cupboardService: CupboardService,
    private fundingbasketService: FundingBasketService,
    private auth: AuthService,
    private changeDetection: ChangeDetectorRef,
    private basketService: BasketService
  ) {}
  needs$!: Observable<Need[]>;
  private searchTerms = new Subject<string>();
  currentNeeds: Need[] = [];
  basket: Need[] = [];

  ngOnInit(): void {
    this.getEntireCupboard();
    this.needs$ = this.searchTerms.pipe(
      distinctUntilChanged(),
      switchMap((term: string) => this.cupboardService.searchNeeds(term))
    );
    this.basketService
      .getBasket()
      .subscribe((basket) => (this.basket = basket));
  }
  search(term: string): void {
    setTimeout(() => {
      this.searchTerms.next(term);
    }, 150);
  }
  getEntireCupboard(): void {
    this.cupboardService
      .getEntireCupboard()
      .subscribe((need) => (this.currentNeeds = [...need]));
  }
  addToBasket(need: Need): void {
    if (!need) return;
    this.basketService.addToBasket(need);
    setTimeout(() => {
      this.fundingbasketService
        .getBasket(this.auth.getUsername())
        .subscribe((basket) => (this.basket = basket));
    }, 30);
    this.changeDetection.detectChanges();
    const msg = document.getElementById(need.id);
    this.basketService
      .getBasket()
      .subscribe((basket) => (this.basket = basket));
    let flag: boolean = false;
    for (let x = 0; x < this.basket.length; x++) {
      if (this.basket[x].id == need.id) {
        if (this.basket[x].quantity == need.quantity) {
          if (msg) {
            if (msg.innerText == '')
              setTimeout(() => {
                msg.innerText = '';
              }, 750);
            msg.innerText = 'You have added the max quantity of this need!';
            msg.style.color = 'rgb(129, 3, 3)';
            return;
          }
        }
        if (msg) {
          if (msg.innerText == '')
            setTimeout(() => {
              msg.innerText = '';
            }, 750);
          msg.innerText = `Thank you for the support. \nYou have added this need ${
            this.basket[x].quantity + 1
          } times`;
          msg.style.color = 'green';
          return;
        }
      }
    }
    if (msg) {
      if (msg.innerText == '')
        setTimeout(() => {
          msg.innerText = '';
        }, 750);
      msg.innerText = `Thank you for the support. \nYou have added this need 1 time`;
      msg.style.color = 'green';
    }
  }

  getNeedsImage(need: Need): string {
    return need.imgSrc;
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
    this.changeDetection.detectChanges();
  };
}
