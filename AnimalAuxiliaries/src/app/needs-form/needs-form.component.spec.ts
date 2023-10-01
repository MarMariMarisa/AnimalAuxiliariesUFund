import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NeedsFormComponent } from './needs-form.component';

describe('NeedsFormComponent', () => {
  let component: NeedsFormComponent;
  let fixture: ComponentFixture<NeedsFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NeedsFormComponent]
    });
    fixture = TestBed.createComponent(NeedsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
