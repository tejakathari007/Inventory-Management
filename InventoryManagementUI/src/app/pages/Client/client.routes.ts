import { Routes } from '@angular/router';
import { ClientListComponent } from './client-list/client-list.component';
import { ClientEditComponent } from './client-edit/client-edit.component';

export const CLIENT_ROUTES: Routes = [
  {
    path: 'clients',
    component: ClientListComponent
  },
  {
    path: 'clients/:id',
    component: ClientEditComponent
  }
];
