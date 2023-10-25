// import { Component } from '@angular/core';
// import { Need } from '../need';
// import { CupboardService } from '../cupboard.service';
// import { FundingBasketService } from '../funding-basket.service';

// @Component({
//   selector: 'app-funding-basket',
//   templateUrl: './funding-basket.component.html',
//   styleUrls: ['./funding-basket.component.css']
// })
// export class FundingBasketComponent {
//   needs: Need[] = [];

//   constructor(private fundingService: FundingBasketService) {}

//   ngOnInit(): void {
//     this.getNeeds();
//   }

//   getBasket(): void {
//     this.fundingService
//       .getEntireCupboard()
//       .subscribe((needs) => (this.needs = needs));
//   }

//   add(name: string, id : string): void {
//     name = name.trim();
//     if (!name) {
//       return;
//     }
//     if (!id) {
//       return;
//     }
//     let a = JSON.parse(
//       JSON.stringify({
//         id: id,
//         name: name,
//         description: 'descirption',
//         type: 'type',
//         price: 5,
//         quantity: 25,
//         numInBaskets: 5,
//         quantityFunded: 2,
//       } as Need)
//     );
//     console.log(a);
//     this.fundingService.createNeed(a).subscribe((need) => {
//       this.needs.push(need);
//     });
//   }

//   delete(need: Need): void {
//     this.needs = this.needs.filter((h) => h !== need);
//     this.fundingService.deleteNeed(need.id).subscribe();
//   }
// }
