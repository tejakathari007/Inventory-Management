import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowserdbComponent } from './browserdb.component';

describe('BrowserdbComponent', () => {
  let component: BrowserdbComponent;
  let fixture: ComponentFixture<BrowserdbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BrowserdbComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BrowserdbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
