import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServerdbComponent } from './serverdb.component';

describe('ServerdbComponent', () => {
  let component: ServerdbComponent;
  let fixture: ComponentFixture<ServerdbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServerdbComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ServerdbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
