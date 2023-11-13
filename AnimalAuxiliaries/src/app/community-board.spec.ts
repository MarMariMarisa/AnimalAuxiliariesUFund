import { TestBed } from '@angular/core/testing';

import { CommunityBoardService } from './community-board.service';

describe('CommunityBoardServiceService', () => {
  let service: CommunityBoardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommunityBoardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
