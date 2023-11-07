import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
})
export class NavComponent {
  constructor(private router: Router) {}
  logout(): void {
    this.router.navigate(['/login']);
  }
  basket(): void {
    this.router.navigate(['/basket']);
  }
  moveToNeeds(): void {
    this.router.navigate(['/helper']);
  }
}
