import { TestBed } from '@angular/core/testing';

import { CentralServiceService } from './central-service.service';

describe('CentralServiceService', () => {
  let service: CentralServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CentralServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
