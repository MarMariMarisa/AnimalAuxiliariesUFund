import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HelperComponent } from './helper.component';
import { CupboardComponent } from '../cupboard/cupboard.component';

describe('HelperComponent', () => {
  let component: HelperComponent;
  let fixture: ComponentFixture<HelperComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HelperComponent]
    });
    fixture = TestBed.createComponent(HelperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
