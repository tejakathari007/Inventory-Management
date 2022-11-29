import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { BrowsListComponent } from './brows-list.component';
import { BrowsService } from '../brows.service';

describe('BrowsListComponent', () => {
  let component: BrowsListComponent;
  let fixture: ComponentFixture<BrowsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [BrowsListComponent],
      imports: [FormsModule, HttpClientTestingModule, RouterTestingModule],
      providers: [BrowsService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrowsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
