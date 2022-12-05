import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DevicedbComponent } from './devicedb.component';

describe('DevicedbComponent', () => {
  let component: DevicedbComponent;
  let fixture: ComponentFixture<DevicedbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DevicedbComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DevicedbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
