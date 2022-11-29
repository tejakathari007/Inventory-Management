import { TestBed } from '@angular/core/testing';
import { ServerService } from './server.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('ServerService', () => {
  let service: ServerService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ServerService]
    });

    service = TestBed.get(ServerService);
    httpMock = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
