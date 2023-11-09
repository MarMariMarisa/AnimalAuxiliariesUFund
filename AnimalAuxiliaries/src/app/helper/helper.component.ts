import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-helper',
  templateUrl: './helper.component.html',
  styleUrls: ['./helper.component.css'],
})
export class HelperComponent {
  constructor(private router: Router, private auth: AuthService) {}

  ngOnInit(): void {
    // if (this.auth.getUsername() == '') this.router.navigate(['/login']);
  }
}
