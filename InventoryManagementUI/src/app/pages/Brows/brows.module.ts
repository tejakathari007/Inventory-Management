import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BrowsListComponent } from './brows-list/brows-list.component';
import { BrowsEditComponent } from './brows-edit/brows-edit.component';
import { BrowsService } from './brows.service';
import { BROWS_ROUTES } from './brows.routes';
import { NbInputModule, NbCardModule, NbButtonModule, NbActionsModule, NbUserModule, NbCheckboxModule, NbRadioModule, NbDatepickerModule, NbSelectModule, NbIconModule } from '@nebular/theme';
import { ThemeModule } from '../../@theme/theme.module';
import { FormsRoutingModule } from '../forms/forms-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(BROWS_ROUTES),
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
    BrowsListComponent,
    BrowsEditComponent
  ],
  providers: [BrowsService],
  exports: []
})
export class BrowsModule { }
