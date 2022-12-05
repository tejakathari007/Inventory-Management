import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { OthersEditComponent } from './others-edit.component';
import { OthersService } from '../others.service';

describe('OthersEditComponent', () => {
  let component: OthersEditComponent;
  let fixture: ComponentFixture<OthersEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [OthersEditComponent],
      imports: [FormsModule, HttpClientTestingModule, RouterTestingModule],
      providers: [OthersService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OthersEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
