import { TestBed } from '@angular/core/testing';
import { BrowsService } from './brows.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('BrowsService', () => {
  let service: BrowsService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [BrowsService]
    });

    service = TestBed.get(BrowsService);
    httpMock = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
