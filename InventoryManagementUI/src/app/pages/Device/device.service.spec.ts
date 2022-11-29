import { TestBed } from '@angular/core/testing';
import { DeviceService } from './device.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('DeviceService', () => {
  let service: DeviceService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [DeviceService]
    });

    service = TestBed.get(DeviceService);
    httpMock = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
