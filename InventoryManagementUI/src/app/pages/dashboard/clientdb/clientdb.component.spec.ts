import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientdbComponent } from './clientdb.component';

describe('ClientdbComponent', () => {
  let component: ClientdbComponent;
  let fixture: ComponentFixture<ClientdbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientdbComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientdbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
