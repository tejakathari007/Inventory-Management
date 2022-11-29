import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { BrowsEditComponent } from './brows-edit.component';
import { BrowsService } from '../brows.service';

describe('BrowsEditComponent', () => {
  let component: BrowsEditComponent;
  let fixture: ComponentFixture<BrowsEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [BrowsEditComponent],
      imports: [FormsModule, HttpClientTestingModule, RouterTestingModule],
      providers: [BrowsService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrowsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
