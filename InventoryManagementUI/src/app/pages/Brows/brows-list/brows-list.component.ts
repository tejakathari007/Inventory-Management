import { Component, OnInit } from '@angular/core';
import { BrowsFilter } from '../brows-filter';
import { BrowsService } from '../brows.service';
import { Brows } from '../brows';

@Component({
  selector: 'app-brows',
  templateUrl: 'brows-list.component.html'
})
export class BrowsListComponent implements OnInit {
  viewType = 'listview';
  filter = new BrowsFilter();
  selectedBrows!: Brows;
  feedback: any = {};

  get browsList(): Brows[] {
    return this.browsService.browsList;
  }

  constructor(private browsService: BrowsService) {
  }

  ngOnInit() {
    this.search();
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
  search(): void {
    this.browsService.load(this.filter);
  }

  select(selected: Brows): void {
    this.selectedBrows = selected;
  }

  delete(brows: Brows): void {
    if (confirm('Are you sure?')) {
      this.browsService.delete(brows).subscribe({
        next: () => {
          this.feedback = {type: 'success', message: 'Delete was successful!'};
          setTimeout(() => {
            this.search();
          }, 1000);
        },
        error: err => {
          this.feedback = {type: 'warning', message: 'Error deleting.'};
        }
      });
    }
  }
}
