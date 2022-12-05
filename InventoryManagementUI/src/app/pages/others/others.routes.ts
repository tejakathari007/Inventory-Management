import { Routes } from '@angular/router';
import { OthersListComponent } from './others-list/others-list.component';
import { OthersEditComponent } from './others-edit/others-edit.component';

export const OTHERS_ROUTES: Routes = [
  {
    path: 'otherses',
    component: OthersListComponent
  },
  {
    path: 'otherses/:id',
    component: OthersEditComponent
  }
];
