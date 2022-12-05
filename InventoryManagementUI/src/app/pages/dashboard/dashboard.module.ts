import { NgModule } from '@angular/core';
import {
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbTabsetModule,
  NbUserModule,
  NbRadioModule,
  NbSelectModule,
  NbListModule,
  NbIconModule,
  NbAccordionModule,
} from '@nebular/theme';
import { NgxEchartsModule } from 'ngx-echarts';

import { ThemeModule } from '../../@theme/theme.module';
import { DashboardComponent } from './dashboard.component';
import { FormsModule } from '@angular/forms';
import { ServerdbComponent } from './serverdb/serverdb.component';
import { DevicedbComponent } from './devicedb/devicedb.component';
import { ClientdbComponent } from './clientdb/clientdb.component';
import { BrowserdbComponent } from './browserdb/browserdb.component';
import { StatusCardComponent } from './status-card/status-card.component';

@NgModule({
  imports: [
    FormsModule,
    ThemeModule,
    NbCardModule,
    NbUserModule,
    NbButtonModule,
    NbTabsetModule,
    NbActionsModule,
    NbRadioModule,
    NbSelectModule,
    NbListModule,
    NbIconModule,
    NbButtonModule,
    NbAccordionModule,
    NgxEchartsModule,
  ],
  declarations: [
    DashboardComponent,
    StatusCardComponent,
    ServerdbComponent,
    DevicedbComponent,
    ClientdbComponent,
    BrowserdbComponent,
  ],
})
export class DashboardModule { }
