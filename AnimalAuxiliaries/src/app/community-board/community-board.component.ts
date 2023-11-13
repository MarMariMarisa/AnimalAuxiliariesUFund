
import { ChangeDetectorRef, Component } from '@angular/core';
import { CommunityBoardService } from '../community-board.service';
import { Post } from '../post';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-community-board',
  templateUrl: './community-board.component.html',
  styleUrls: ['./community-board.component.css']
})
export class CommunityBoardComponent {
  constructor(
    private communityBoardService: CommunityBoardService,
    private changeDetection: ChangeDetectorRef,
  ) {}
  posts$!: Observable<Post[]>;
  currentPosts: Post[] = [];

  ngOnInit(): void {
    this.getCommunityBoard();
   
  }
  getCommunityBoard(): void {
    this.communityBoardService
      .getCommunityBoard()
      .subscribe((post) => (this.currentPosts = [...post]));
  }

}
