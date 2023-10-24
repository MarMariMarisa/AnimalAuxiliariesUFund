import { Component, OnInit } from '@angular/core';
import {Cupboard} from '../cupboard';
import { CupboardService } from '../cupboard.service';
import { Need } from '../need';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-cupboard',
  templateUrl: './cupboard.component.html',
  styleUrls: ['./cupboard.component.css']
})
export class CupboardComponent implements OnInit {
  constructor(private cupboardService: CupboardService) { }

    currentNeeds: Need[] = [];

  ngOnInit(): void {
    this.getEntireCupboard();
  }

  getEntireCupboard(): void {
    this.cupboardService.getEntireCupboard().subscribe((need)=> (this.currentNeeds = need));
  }
  

}
