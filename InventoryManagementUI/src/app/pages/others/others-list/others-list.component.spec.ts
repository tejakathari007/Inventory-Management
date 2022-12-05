import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { OthersListComponent } from './others-list.component';
import { OthersService } from '../others.service';

describe('OthersListComponent', () => {
  let component: OthersListComponent;
  let fixture: ComponentFixture<OthersListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [OthersListComponent],
      imports: [FormsModule, HttpClientTestingModule, RouterTestingModule],
      providers: [OthersService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OthersListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
