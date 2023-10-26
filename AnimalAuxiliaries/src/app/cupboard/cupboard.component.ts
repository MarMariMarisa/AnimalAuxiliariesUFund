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
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.cupboardService.searchNeeds(term))
    );
    this.basketService
      .getBasket()
      .subscribe((basket) => (this.basket = basket));
  }
  search(term: string): void {
    this.searchTerms.next(term);
  }
  getEntireCupboard(): void {
    this.cupboardService
      .getEntireCupboard()
      .subscribe((need) => (this.currentNeeds = need));
  }
  addToBasket(need: Need): void {
    this.basketService.addToBasket(need);
    setTimeout(() => {
      this.fundingbasketService
        .getBasket(this.auth.getUsername())
        .subscribe((basket) => (this.basket = basket));
    }, 30);
    this.changeDetection.detectChanges();
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
