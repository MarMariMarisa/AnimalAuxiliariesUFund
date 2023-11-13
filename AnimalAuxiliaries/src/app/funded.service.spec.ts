import { TestBed } from '@angular/core/testing';

import { FundedService } from './funded.service';

describe('FundedService', () => {
  let service: FundedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FundedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
