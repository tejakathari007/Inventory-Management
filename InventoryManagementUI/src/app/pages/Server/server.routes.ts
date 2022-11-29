import { Routes } from '@angular/router';
import { ServerListComponent } from './server-list/server-list.component';
import { ServerEditComponent } from './server-edit/server-edit.component';

export const SERVER_ROUTES: Routes = [
  {
    path: 'servers',
    component: ServerListComponent
  },
  {
    path: 'servers/:id',
    component: ServerEditComponent
  },
  {
    path: 'servers/:id/:type',
    component: ServerEditComponent
  }
];
