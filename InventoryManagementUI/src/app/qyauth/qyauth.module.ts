import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { NbAlertModule, NbInputModule, NbButtonModule, 
  NbCheckboxModule, NbActionsModule, NbCardModule,
   NbDatepickerModule, NbIconModule, NbRadioModule,
    NbSelectModule, NbUserModule } from '@nebular/theme';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from './register/register.component';
import { NbAuthModule, NbLoginComponent } from '@nebular/auth';
import { FormsRoutingModule } from '../pages/forms/forms-routing.module';
import { qyauthRoutingModule } from './qyauth.route.module';
import { BrowserModule } from '@angular/platform-browser';
import { QyauthComponent } from './qyauth/qyauth.component';
import { ThemeModule } from '../@theme/theme.module';

// const routes: Routes = [
//   {
//     path: '',
//     component: LoginComponent,
//   },
//   {
//     path: 'login',
//     component: LoginComponent,
//     // NbLoginComponent
//   },
//   {
//     path: 'register',
//     component: RegisterComponent,
//   },
//   {
//     path: '',
//     redirectTo: 'login',
//     pathMatch: 'full',
//   },
// ];


@NgModule({
  declarations: [LoginComponent,RegisterComponent,QyauthComponent],
  imports: [
    qyauthRoutingModule,
    FormsModule,
    ThemeModule,
    RouterModule,
    NbAlertModule,
    NbInputModule,
    NbButtonModule,
    NbCheckboxModule,
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
  ]
})
export class QyauthModule { }


