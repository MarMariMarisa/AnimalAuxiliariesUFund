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
    private changeDetection: ChangeDetectorRef
  ) {}
  needs$!: Observable<Need[]>;
  private searchTerms = new Subject<string>();
  currentNeeds: Need[] = [];
  basket: Need[] = [];

  ngOnInit(): void {
    this.getEntireCupboard();
    this.getBasket();
    this.needs$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.cupboardService.searchNeeds(term))
    );
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
    this.fundingbasketService
      .addToBasket(this.auth.getUsername(), need)
      .subscribe((basket) => {
        this.basket = [...this.basket, basket];
      });
    this.fundingbasketService
      .getBasket(this.auth.getUsername())
      .subscribe((need) => (this.basket = need));
  }
  getBasket(): void {
    this.fundingbasketService
      .getBasket(this.auth.getUsername())
      .subscribe((need) => (this.basket = need));
  }

  removeFromBasket = async (need: Need) => {
    this.fundingbasketService
      .removeFromBasket(this.auth.getUsername(), need.id)
      .subscribe((basket) => {
        basket;
      });
    this.fundingbasketService
      .getBasket(this.auth.getUsername())
      .subscribe((need) => (this.basket = need));
    this.fundingbasketService
      .getBasket(this.auth.getUsername())
      .subscribe((need) => (this.basket = need));
  };
}
