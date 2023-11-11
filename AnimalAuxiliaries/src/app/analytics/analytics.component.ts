import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { FundingBasketService } from '../funding-basket.service';
import { FundedService } from '../funded.service';
import { Need } from '../need';
@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.css'],
})
export class AnalyticsComponent {
  constructor(
    private router: Router,
    private auth: AuthService,
    private fund: FundedService
  ) {}
  funded: Need[] = [];
  ngOnInit(): void {
    this.getFunded();
    if (this.auth.getUsername() != 'admin') this.router.navigate(['/login']);
  }
  getFunded() {
    this.fund.getFunded().subscribe((funded) => (this.funded = [...funded]));
    console.log(this.funded);
  }
}
