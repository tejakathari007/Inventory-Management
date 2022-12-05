import { NgModule } from '@angular/core';
import { NbAccordionModule, NbActionsModule, NbAutocompleteModule, NbButtonModule, NbCardModule, NbCheckboxModule, NbDatepickerModule, NbIconModule, NbInputModule, NbMenuModule, NbRadioModule, NbSelectModule, NbUserModule } from '@nebular/theme';

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
import { AuditComponent } from './audit/audit.component';
import { FormsRoutingModule } from './forms/forms-routing.module';
import { NgxPaginationModule } from 'ngx-pagination';
import { ArvrComponent } from './arvr/arvr.component';
import { QRCodeModule } from 'angularx-qrcode';
import { TreeviewComponent } from './treeview/treeview.component';
import { NgxEchartsModule } from 'ngx-echarts';

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
    BrowsModule,
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
    NgxPaginationModule,
    QRCodeModule,
    NbAccordionModule,
    NgxEchartsModule,
    NbAutocompleteModule
  ],
  declarations: [
    PagesComponent,
    AuditComponent,
    ArvrComponent,
    TreeviewComponent,
  ],
})
export class PagesModule {
}
