import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NeedComponent } from './need.component';

describe('NeedComponent', () => {
  let component: NeedComponent;
  let fixture: ComponentFixture<NeedComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NeedComponent]
    });
    fixture = TestBed.createComponent(NeedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
