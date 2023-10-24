import { Component,OnInit } from '@angular/core';
import { Need } from '../need' 
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { CupboardService } from '../cupboard.service';
@Component({
  selector: 'app-need',
  templateUrl: './need.component.html',
  styleUrls: ['./need.component.css']
})
export class NeedComponent implements OnInit{
  need : Need | undefined;
  constructor(
    private route: ActivatedRoute,
    private cupboardService: CupboardService,
    private location: Location
  ) {}
  ngOnInit(): void {
    this.getNeed();
  }
  getNeed(): void {
    const id = this.route.snapshot.paramMap.get('id')!;
    console.log(id);
    this.cupboardService.getNeed(id).subscribe((need) => (this.need = need));
  }

  goBack(): void {
    this.location.back();
  }
  save(): void {
    if (this.need) {
      this.cupboardService.updateNeed(this.need).subscribe(() => this.goBack());
    }
  }
}
