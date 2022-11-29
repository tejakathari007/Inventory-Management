import { NgModule } from '@angular/core';
import { NbMenuModule } from '@nebular/theme';

import { ThemeModule } from '../@theme/theme.module';
import { PagesComponent } from './pages.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { ECommerceModule } from './e-commerce/e-commerce.module';
import { PagesRoutingModule } from './pages-routing.module';
import { MiscellaneousModule } from './miscellaneous/miscellaneous.module';
import { BrowsModule } from './Brows/brows.module';
import { ClientModule } from './Client/client.module';
import { DeviceModule } from './Device/device.module';
import { ServerModule } from './Server/server.module';

@NgModule({
  imports: [
    PagesRoutingModule,
    ThemeModule,
    NbMenuModule,
    DashboardModule,
    ECommerceModule,
    MiscellaneousModule,
    ServerModule,
    ClientModule,
    DeviceModule,
    BrowsModule
  ],
  declarations: [
    PagesComponent,
  ],
})
export class PagesModule {
}
