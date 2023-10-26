import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-helper',
  templateUrl: './helper.component.html',
  styleUrls: ['./helper.component.css'],
})
export class HelperComponent {
  constructor(private router: Router) {}
  logout(): void {
    this.router.navigate(['/login']);
  }
}
