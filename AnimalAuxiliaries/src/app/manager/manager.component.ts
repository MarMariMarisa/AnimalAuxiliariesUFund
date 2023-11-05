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

  add(name: string): void {
    name = name.trim();
    if (!name) {
      return;
    }

    let a = JSON.parse(
      JSON.stringify({
        id: '',
        name: name,
        description: 'description',
        type: 'type',
        price: 5,
        quantity: 25,
        numInBaskets: 5,
        quantityFunded: 2,
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
