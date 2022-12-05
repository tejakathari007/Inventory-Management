import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { OthersListComponent } from './others-list/others-list.component';
import { OthersEditComponent } from './others-edit/others-edit.component';
import { OthersService } from './others.service';
import { OTHERS_ROUTES } from './others.routes';
import { NbInputModule, NbCardModule, NbButtonModule, NbActionsModule, NbUserModule, NbCheckboxModule, NbRadioModule, NbDatepickerModule, NbSelectModule, NbIconModule } from '@nebular/theme';
import { ThemeModule } from '../../@theme/theme.module';
import { FormsRoutingModule } from '../forms/forms-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(OTHERS_ROUTES),
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
    OthersListComponent,
    OthersEditComponent
  ],
  providers: [OthersService],
  exports: []
})
export class OthersModule { }
