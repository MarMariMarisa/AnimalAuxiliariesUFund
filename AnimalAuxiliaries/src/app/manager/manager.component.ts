import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { CupboardService } from '../cupboard.service';
import { Router, TitleStrategy } from '@angular/router';
import { AuthService } from '../auth.service';
import { FundingBasketService } from '../funding-basket.service';
import { Observable, Subject, of } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { CommunityBoardService } from '../community-board.service';
import { Post } from '../post';
@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css'],
})
export class ManagerComponent implements OnInit {
  needs: Need[] = [];
  posts: Post[] = [];
  deleteConfirm: Need | null = null;
  needs$!: Observable<Need[]>;
  private searchTerms = new Subject<string>();
  constructor(
    private needService: CupboardService,
    private communityBoardService: CommunityBoardService,
    private router: Router,
    private auth: AuthService,
    private basket: FundingBasketService
  ) {}

  ngOnInit(): void {
    this.getNeeds();
    this.needs$ = this.searchTerms.pipe(
      distinctUntilChanged(),
      switchMap((term: string) => this.needService.searchNeeds(term))
    );
    if (this.auth.getUsername() != 'admin') this.router.navigate(['/login']);
  }
  logout(): void {
    this.router.navigate(['/login']);
  }
  search(term: string): void {
    setTimeout(() => {
      this.searchTerms.next(term);
    }, 150);
  }
  getNeeds(): void {
    this.needService
      .getEntireCupboard()
      .subscribe((needs) => (this.needs = needs));
  }
  save(need: Need): void {
    if (need) {
      this.needService.updateNeed(need).subscribe();
      this.basket.removeFromBasket(this.auth.getUsername(), need.id);
      const sb: HTMLInputElement | null = <HTMLInputElement>(
        document.getElementById('search-box')
      );
      if (sb) {
        const temp: string = sb.value;
        sb.value = '';
        setTimeout(() => {
          sb.value = temp;
          setTimeout(() => {
            this.searchTerms.next(temp);
          }, 5);
        }, 5);
      }
    }
  }
  add(
    name: string,
    description: string,
    type: string,
    price: string,
    quantity: string,
    imgSrc: string
  ): void {
    name = name.trim();
    if (!name) {
      return;
    }
    const errorMessage = document.getElementById('errorMessage');
    let aPrice = parseInt(price);
    let aQuant = parseInt(quantity);
    console.log(description);
    if (
      name == '' ||
      description == '' ||
      type == '' ||
      price == '' ||
      quantity == ''
    ) {
      if (errorMessage) {
        errorMessage.textContent = 'Fields cannot be empty.';
        errorMessage.style.color = '#c91d06';
        return;
      }
    } else if (aPrice <= 0) {
      if (errorMessage) {
        errorMessage.textContent = 'Price cannot be less than or equal to 0';
        errorMessage.style.color = '#c91d06';
        return;
      }
    } else if (aQuant <= 0) {
      if (errorMessage) {
        errorMessage.textContent = 'Quantity cannot be less than or equal to 0';
        errorMessage.style.color = '#c91d06';
        return;
      }
    } else {
      if (errorMessage) {
        errorMessage.textContent = '';
      }
    }
    let a = JSON.parse(
      JSON.stringify({
        id: '',
        name: name,
        description: description,
        type: type,
        price: aPrice,
        quantity: aQuant,
        numInBaskets: 0,
        quantityFunded: 0,
        imgSrc: imgSrc,
      } as Need)
    );
    this.needService.createNeed(a).subscribe((need) => {
      this.needs.push(need);
    });
    const sb: HTMLInputElement | null = <HTMLInputElement>(
      document.getElementById('search-box')
    );
    if (sb) {
      const temp: string = sb.value;
      sb.value = '';
      setTimeout(() => {
        sb.value = temp;
        setTimeout(() => {
          this.searchTerms.next(temp);
        }, 5);
      }, 5);
    }
  }

  delete(need: Need): void {
    this.needService.deleteNeed(need.id).subscribe(() => {
      const sb: HTMLInputElement | null = <HTMLInputElement>(
        document.getElementById('search-box')
      );
      if (sb) {
        const temp: string = sb.value;
        sb.value = '';
        setTimeout(() => {
          sb.value = temp;
          setTimeout(() => {
            this.searchTerms.next(temp);
          }, 5);
        }, 5);
      }
    });
    setTimeout(() => {
      this.needs = this.needs.filter((h) => h.id != need.id);
    }, 50);
  }
  onPress(need: Need) {
    need.display = !need.display;
  }

  deletePost(post: Post): void {
    this.communityBoardService.deletePost(post.title).subscribe();
  }
  addPost(
    title: string,
    content: string,
  ): void {
    title = title.trim();
    if (!title) {
      return;
    }
    const errorMessage = document.getElementById('errorMessage');
    console.log(content);
    if (
      title == '' ||
      content == ''
    ) {
      if (errorMessage) {
        errorMessage.textContent = 'Fields cannot be empty.';
        errorMessage.style.color = '#c91d06';
        return;
      }
    } else {
      if (errorMessage) {
        errorMessage.textContent = '';
      }
    }
    let a = JSON.parse(
      JSON.stringify({
        id: '',
        title: title,
        content: content,
      } as Post)
    );
    this.communityBoardService.createPost(a).subscribe((post) => {
      this.posts.push(post);
    });
}
}
