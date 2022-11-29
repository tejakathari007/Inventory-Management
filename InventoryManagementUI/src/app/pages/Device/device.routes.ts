import { Routes } from '@angular/router';
import { DeviceListComponent } from './device-list/device-list.component';
import { DeviceEditComponent } from './device-edit/device-edit.component';

export const DEVICE_ROUTES: Routes = [
  {
    path: 'devices',
    component: DeviceListComponent
  },
  {
    path: 'devices/:id',
    component: DeviceEditComponent
  }
];
