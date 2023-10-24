import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { CupboardService } from '../cupboard.service';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css'],
})
export class ManagerComponent implements OnInit {
  needs: Need[] = [];

  constructor(private needService: CupboardService) {}

  ngOnInit(): void {
    this.getNeeds();
  }

  getNeeds(): void {
    this.needService
      .getEntireCupboard()
      .subscribe((needs) => (this.needs = needs));
  }

  add(name: string): void {
    name = name.trim();
    if (!name) {
      return;
    }
    this.needService
      .createNeed({
        id: '5',
        name: 'hello',
        description: 'descirption',
        type: 'type',
        price: 5,
        quantity: 25,
        numInBaskets: 5,
        quantityFunded: 2,
      } as Need)
      .subscribe((need) => {
        console.log(typeof need);
        this.needs.push(need);
      });
  }

  delete(need: Need): void {
    this.needs = this.needs.filter((h) => h !== need);
    this.needService.deleteNeed(need.id).subscribe();
  }
}
