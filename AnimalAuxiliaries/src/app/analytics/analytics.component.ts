import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { FundingBasketService } from '../funding-basket.service';
import { FundedService } from '../funded.service';
import { Need } from '../need';
import { CupboardService } from '../cupboard.service';
@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.css'],
})
export class AnalyticsComponent {
  constructor(
    private router: Router,
    private auth: AuthService,
    private fund: FundedService,
    private cupboard: CupboardService
  ) {}
  funded: Need[] = [];
  needs: Need[] = [];
  val = 0;
  ngOnInit(): void {
    this.getFunded();
    this.cupboard.getEntireCupboard().subscribe((cupboard) => {
      this.needs = [...cupboard];
    });
    this.cupboard.getSurplus().subscribe((val) => (this.val = val));
    const surp = document.getElementById('surplus');
    if (surp) {
      setTimeout(() => {
        surp.innerText = `Surplus Raised: $${this.val.toFixed(2)}`;
      }, 50);
    }
    setTimeout(() => {
      const fundedTitle = document.getElementById('fundedTitle');
      const unfundedTitle = document.getElementById('unfundedTitle');
      if (fundedTitle)
        fundedTitle.innerText = `Funded Needs: ${this.funded.length}`;
      if (unfundedTitle)
        unfundedTitle.innerText = `Unfunded Needs: ${this.needs.length}`;
    }, 50);

    // if (this.auth.getUsername() != 'admin') this.router.navigate(['/login']);
    // setTimeout(() => {
    //   const bars = document.getElementsByClassName('progress-bar');
    //   for (let x = 0; x < bars.length; x++) {
    //     const bar = <HTMLElement>bars[x].getElementsByClassName('progress')[0];
    //     const percentage = `${
    //       (this.funded[x].quantity / this.funded[x].quantityFunded) * 100
    //     }%`;
    //     bar.innerText = percentage;
    //     bar.style.width = percentage;
    //   }
    // }, 50);
  }
  getFunded() {
    this.fund.getFunded().subscribe((funded) => (this.funded = [...funded]));
  }
}
