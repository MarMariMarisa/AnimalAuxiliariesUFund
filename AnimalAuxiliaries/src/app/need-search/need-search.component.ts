import { Component, OnInit } from '@angular/core';

import { Need } from '../need';
import { Observable, Subject } from 'rxjs';
import { CupboardService } from '../cupboard.service';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';

import { FundingBasketService } from '../funding-basket.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-need-search',
  templateUrl: './need-search.component.html',
  styleUrls: ['./need-search.component.css'],
})
export class NeedSearchComponent implements OnInit {
  needs$!: Observable<Need[]>;
  private searchTerms = new Subject<string>();

  constructor(
    private cupboardService: CupboardService,
    private fundingbasketService: FundingBasketService,
    private auth: AuthService
    ) {
  }

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  addToBasket(need: Need): void {
    this.fundingbasketService
      .addToBasket(this.auth.getUsername(),need)
      .subscribe((newNeed) => newNeed);
  }
  ngOnInit(): void {
    this.needs$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.cupboardService.searchNeeds(term))
    );
  }
}