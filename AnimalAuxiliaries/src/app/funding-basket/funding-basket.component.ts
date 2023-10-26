import { ChangeDetectorRef, Component } from '@angular/core';
import { Need } from '../need';
import { FundingBasketService } from '../funding-basket.service';
import { LoginComponent } from '../login/login.component';
import { AuthService } from '../auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-funding-basket',
  templateUrl: './funding-basket.component.html',
  styleUrls: ['./funding-basket.component.css'],
})
export class FundingBasketComponent {
  constructor(
    private fundingBasketService: FundingBasketService,
    private auth: AuthService,
    private route: ActivatedRoute,
    private changeDetectorRef: ChangeDetectorRef
  ) {}
  basket: Need[] = [];
}
