import { TestBed } from '@angular/core/testing';
import { OthersService } from './others.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('OthersService', () => {
  let service: OthersService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [OthersService]
    });

    service = TestBed.get(OthersService);
    httpMock = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
