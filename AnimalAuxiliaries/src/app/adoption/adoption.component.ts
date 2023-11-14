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
  animal: Animal;
  ngOnInit(): void {
    this.adoptionService
      .getAnimals()
      .subscribe((animals) => (this.animals = [...animals]));
    console.log(this.animals);
  }
}
