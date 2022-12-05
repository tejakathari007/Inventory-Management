import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { DeviceListComponent } from './device-list/device-list.component';
import { DeviceEditComponent } from './device-edit/device-edit.component';
import { DeviceService } from './device.service';
import { DEVICE_ROUTES } from './device.routes';
import { NbInputModule, NbCardModule, NbButtonModule, NbActionsModule, NbUserModule, NbCheckboxModule, NbRadioModule, NbDatepickerModule, NbSelectModule, NbIconModule, NbAutocompleteModule } from '@nebular/theme';
import { ThemeModule } from '../../@theme/theme.module';
import { FormsRoutingModule } from '../forms/forms-routing.module';
import { QrreadComponent } from './qrread/qrread.component';
import { QRCodeModule } from 'angularx-qrcode';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(DEVICE_ROUTES),
    ThemeModule,
    NbInputModule,
    NbCardModule,
    NbButtonModule,
    NbActionsModule,
    NbUserModule,
    NbCheckboxModule,
    NbRadioModule,
    NbDatepickerModule,
    FormsRoutingModule,
    NbSelectModule,
    NbIconModule,
    QRCodeModule,
    NbAutocompleteModule
  ],
  declarations: [
    DeviceListComponent,
    DeviceEditComponent,
    QrreadComponent
  ],
  providers: [DeviceService],
  exports: []
})
export class DeviceModule { }
