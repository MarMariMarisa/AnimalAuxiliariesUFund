import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { CupboardService } from '../cupboard.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { FundingBasketService } from '../funding-basket.service';
import { Observable, Subject, of } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css'],
})
export class ManagerComponent implements OnInit {
  needs: Need[] = [];
  deleteConfirm: Need | null = null;
  needs$!: Observable<Need[]>;
  private searchTerms = new Subject<string>();
  constructor(
    private needService: CupboardService,
    private router: Router,
    private auth: AuthService,
    private basket: FundingBasketService
  ) {}

  ngOnInit(): void {
    this.getNeeds();
    this.needs$ = this.searchTerms.pipe(
      distinctUntilChanged(),
      switchMap((term: string) => this.needService.searchNeeds(term))
    );
    if (this.auth.getUsername() != 'admin') this.router.navigate(['/login']);
  }
  logout(): void {
    this.router.navigate(['/login']);
  }
  search(term: string): void {
    setTimeout(() => {
      this.searchTerms.next(term);
    }, 150);
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
      const sb: HTMLInputElement | null = <HTMLInputElement>(
        document.getElementById('search-box')
      );
      if (sb) {
        const temp: string = sb.value;
        sb.value = '';
        setTimeout(() => {
          sb.value = temp;
          setTimeout(() => {
            this.searchTerms.next(temp);
          }, 5);
        }, 5);
      }
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
    const sb: HTMLInputElement | null = <HTMLInputElement>(
      document.getElementById('search-box')
    );
    if (sb) {
      const temp: string = sb.value;
      sb.value = '';
      setTimeout(() => {
        sb.value = temp;
        setTimeout(() => {
          this.searchTerms.next(temp);
        }, 5);
      }, 5);
    }
  }

  delete(need: Need): void {
    this.needService.deleteNeed(need.id).subscribe(() => {
      const sb: HTMLInputElement | null = <HTMLInputElement>(
        document.getElementById('search-box')
      );
      if (sb) {
        const temp: string = sb.value;
        sb.value = '';
        setTimeout(() => {
          sb.value = temp;
          setTimeout(() => {
            this.searchTerms.next(temp);
          }, 5);
        }, 5);
      }
    });
    setTimeout(() => {
      this.needs = this.needs.filter((h) => h.id != need.id);
    }, 50);
  }
  onPress(need: Need) {
    need.display = !need.display;
  }
}
