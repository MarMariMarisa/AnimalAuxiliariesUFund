import { Component } from '@angular/core';
import { Animal } from '../animal';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { AdoptionService } from '../adoption.service';

@Component({
  selector: 'app-adoption',
  templateUrl: './adoption.component.html',
  styleUrls: ['./adoption.component.css'],
})
export class AdoptionComponent {
  constructor(
    private adoptionService: AdoptionService,
    private auth: AuthService,
    private router: Router
  ) {}
  animals: Animal[] = [];
  adoptionConfirm: Animal | null = null;
  holdConfirm: Animal | null = null;
  ngOnInit(): void {
    this.adoptionService
      .getAnimals()
      .subscribe((animals) => (this.animals = [...animals]));
    if (this.auth.getUsername() == '') this.router.navigate(['/login']);
    setTimeout(() => {
      const desc = document.getElementsByClassName('description');
      for (let x = 0; x < desc.length; x++) {
        desc[x].addEventListener('click', (e) => {
          console.log('expand');
          desc[x].classList.toggle('toggle-elip');
        });
      }
    }, 45);
  }
  adopt(animal: Animal) {
    this.adoptionService.adopt(animal.id).subscribe((res) => res);
    setTimeout(() => {
      this.adoptionService
        .getAnimals()
        .subscribe((animals) => (this.animals = [...animals]));
    }, 30);
  }
}
