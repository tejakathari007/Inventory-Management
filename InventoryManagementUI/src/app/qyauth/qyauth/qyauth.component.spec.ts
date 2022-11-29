import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QyauthComponent } from './qyauth.component';

describe('QyauthComponent', () => {
  let component: QyauthComponent;
  let fixture: ComponentFixture<QyauthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QyauthComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QyauthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
