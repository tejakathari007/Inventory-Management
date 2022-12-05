import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArvrComponent } from './arvr.component';

describe('ArvrComponent', () => {
  let component: ArvrComponent;
  let fixture: ComponentFixture<ArvrComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArvrComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArvrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
