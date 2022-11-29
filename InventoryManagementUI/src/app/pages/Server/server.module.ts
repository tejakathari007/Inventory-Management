import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ServerListComponent } from './server-list/server-list.component';
import { ServerEditComponent } from './server-edit/server-edit.component';
import { ServerService } from './server.service';
import { SERVER_ROUTES } from './server.routes';
import { NbInputModule, NbCardModule, NbButtonModule, NbActionsModule, 
  NbUserModule, NbCheckboxModule, NbRadioModule, NbDatepickerModule, NbSelectModule, NbIconModule } from '@nebular/theme';
import { ThemeModule } from '../../@theme/theme.module';
import { ButtonsComponent } from '../forms/buttons/buttons.component';
import { DatepickerComponent } from '../forms/datepicker/datepicker.component';
import { FormInputsComponent } from '../forms/form-inputs/form-inputs.component';
import { FormLayoutsComponent } from '../forms/form-layouts/form-layouts.component';
import { FormsRoutingModule } from '../forms/forms-routing.module';
import { FormsComponent } from '../forms/forms.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(SERVER_ROUTES),
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
    NbIconModule
  ],
  declarations: [
    ServerListComponent,
    ServerEditComponent
  ],
  providers: [ServerService],
  exports: []
})
export class ServerModule { }
