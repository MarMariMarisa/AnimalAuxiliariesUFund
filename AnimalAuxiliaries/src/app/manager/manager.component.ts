import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { CupboardService } from '../cupboard.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css'],
})
export class ManagerComponent implements OnInit {
  needs: Need[] = [];

  constructor(private needService: CupboardService, private router: Router) {}

  ngOnInit(): void {
    this.getNeeds();
  }
  logout(): void {
    this.router.navigate(['/login']);
  }

  getNeeds(): void {
    this.needService
      .getEntireCupboard()
      .subscribe((needs) => (this.needs = needs));
  }

<<<<<<< HEAD
  add(name: string,description: string,type:string,price:number,quantity:number): void {
=======
  add(
    name: string,
    desc: string,
    type: string,
    price: string,
    quant: string
  ): void {
>>>>>>> e2645a3daeffaacdae28addc02e1ada7b1f79066
    name = name.trim();
    if (!name) {
      return;
    }
    let aPrice: number = parseInt(price);
    let aQuant: number = parseInt(quant);
    let a = JSON.parse(
      JSON.stringify({
        id: '',
        name: name,
<<<<<<< HEAD
        description:description,
        type: type,
        price: price,
        quantity: quantity,
=======
        description: desc,
        type: type,
        price: aPrice,
        quantity: aQuant,
>>>>>>> e2645a3daeffaacdae28addc02e1ada7b1f79066
        numInBaskets: 0,
        quantityFunded: 0,
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
}
