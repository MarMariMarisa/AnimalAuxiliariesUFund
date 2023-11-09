import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { CupboardService } from '../cupboard.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { FundingBasketService } from '../funding-basket.service';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css'],
})
export class ManagerComponent implements OnInit {
  needs: Need[] = [];
  deleteConfirm: Need | null = null;
  constructor(
    private needService: CupboardService,
    private router: Router,
    private auth: AuthService,
    private basket: FundingBasketService
  ) {}

  ngOnInit(): void {
    this.getNeeds();
    if (this.auth.getUsername() != 'admin') this.router.navigate(['/login']);
  }
  logout(): void {
    this.router.navigate(['/login']);
  }

  getNeeds(): void {
    this.needService
      .getEntireCupboard()
      .subscribe((needs) => (this.needs = needs));
  }
  save(need: Need): void {
    if (need) {
      this.needService.updateNeed(need).subscribe();
      this.basket.removeFromBasket(this.auth.getUsername(), need.id);
    }
  }
  add(
    name: string,
    description: string,
    type: string,
    price: string,
    quantity: string,
    imgSrc: string
  ): void {
    name = name.trim();
    if (!name) {
      return;
    }
    let aPrice = parseInt(price);
    let aQuant = parseInt(quantity);
    if (aPrice <= 0) aPrice = 1;
    if (aQuant <= 0) aQuant = 1;
    let a = JSON.parse(
      JSON.stringify({
        id: '',
        name: name,
        description: description,
        type: type,
        price: aPrice,
        quantity: aQuant,
        numInBaskets: 0,
        quantityFunded: 0,
        imgSrc: imgSrc,
      } as Need)
    );
    this.needService.createNeed(a).subscribe((need) => {
      this.needs.push(need);
    });
  }

  delete(need: Need): void {
    this.needs = this.needs.filter((h) => h !== need);
    this.needService.deleteNeed(need.id).subscribe();
  }
  onPress(need: Need) {
    need.display = !need.display;
  }
}
