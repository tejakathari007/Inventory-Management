import { Routes } from '@angular/router';
import { BrowsListComponent } from './brows-list/brows-list.component';
import { BrowsEditComponent } from './brows-edit/brows-edit.component';

export const BROWS_ROUTES: Routes = [
  {
    path: 'browses',
    component: BrowsListComponent
  },
  {
    path: 'browses/:id',
    component: BrowsEditComponent
  }
];
