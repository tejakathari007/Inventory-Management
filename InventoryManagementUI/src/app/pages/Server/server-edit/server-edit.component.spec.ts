import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ServerEditComponent } from './server-edit.component';
import { ServerService } from '../server.service';

describe('ServerEditComponent', () => {
  let component: ServerEditComponent;
  let fixture: ComponentFixture<ServerEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ServerEditComponent],
      imports: [FormsModule, HttpClientTestingModule, RouterTestingModule],
      providers: [ServerService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServerEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
