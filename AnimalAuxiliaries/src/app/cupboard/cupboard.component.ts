import { Component, OnInit } from '@angular/core';
import { Cupboard } from '../cupboard';
import { CupboardService } from '../cupboard.service';
import { Need } from '../need';
import { Observable } from 'rxjs';
import { FundingBasketService } from '../funding-basket.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-cupboard',
  templateUrl: './cupboard.component.html',
  styleUrls: ['./cupboard.component.css'],
})
export class CupboardComponent implements OnInit {
  constructor(
    private cupboardService: CupboardService,
    private fundingbasketService: FundingBasketService,
    private auth: AuthService
  ) {}

  currentNeeds: Need[] = [];

  ngOnInit(): void {
    this.getEntireCupboard();
  }

  getEntireCupboard(): void {
    this.cupboardService
      .getEntireCupboard()
      .subscribe((need) => (this.currentNeeds = need));
  }
  addToBasket(need: Need): void {
    this.fundingbasketService
      .addToBasket(need.id, this.auth.getUsername())
      .subscribe((newNeed) => newNeed);
  }
}
