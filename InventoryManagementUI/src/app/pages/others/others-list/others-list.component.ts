import { Component, OnInit } from '@angular/core';
import { OthersFilter } from '../others-filter';
import { OthersService } from '../others.service';
import { Others } from '../others';

@Component({
  selector: 'app-others',
  templateUrl: 'others-list.component.html'
})
export class OthersListComponent implements OnInit {
  viewType = 'listview';
  filter = new OthersFilter();
  selectedOthers!: Others;
  feedback: any = {};

  get othersList(): Others[] {
    return this.othersService.othersList;
  }

  constructor(private othersService: OthersService) {
  }

  ngOnInit() {
    this.load();
  }

  load(): void {
    this.othersService.load(this.filter);
  }

  select(selected: Others): void {
    this.selectedOthers = selected;
  }
  search(): void {
    this.othersService.searchfilter(this.filter);
  }
  checkAccess() {
    const userData = JSON.parse(localStorage.getItem('userDetails'));
    if(userData && userData.role=="ROLE_ADMIN"){
      return true;
    }
    else {
      return false;
    }
  }
  delete(others: Others): void {
    if (confirm('Are you sure?')) {
      this.othersService.delete(others).subscribe({
        next: () => {
          this.feedback = {type: 'success', message: 'Delete was successful!'};
          setTimeout(() => {
            this.load();
          }, 1000);
        },
        error: err => {
          this.feedback = {type: 'warning', message: 'Error deleting.'};
        }
      });
    }
  }
}
