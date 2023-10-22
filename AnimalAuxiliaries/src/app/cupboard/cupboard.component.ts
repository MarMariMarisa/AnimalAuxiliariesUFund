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
  cupboard: Cupboard = {
    currentNeeds: [],
    retiredNeeds: []
  }
  ngOnInit(): void {
    this.cupboard.currentNeeds = this.cupboardService.getEntireCupboard;
    this.getEntireCupboard();
  }

  getEntireCupboard(): Observable<Need[]> {
    this.cupboardService.getEntireCupboard();
  }

}
