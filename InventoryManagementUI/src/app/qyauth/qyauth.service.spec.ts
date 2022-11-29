import { TestBed } from '@angular/core/testing';

import { QyauthService } from './qyauth.service';

describe('QyauthService', () => {
  let service: QyauthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QyauthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
