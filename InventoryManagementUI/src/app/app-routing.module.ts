import { ExtraOptions, RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import {
  NbAuthComponent,
  NbLoginComponent,
  NbLogoutComponent,
  NbRegisterComponent,
  NbRequestPasswordComponent,
  NbResetPasswordComponent,
} from '@nebular/auth';
import { AuthGuard } from './auth.guard';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './qyauth/register/register.component';

export const routes: Routes = [
  {
    path: 'pages',
    loadChildren: () => import('./pages/pages.module')
      .then(m => m.PagesModule),
      canActivate: [AuthGuard]
  },
  {
    path: 'auth',
    loadChildren: () => import('./qyauth/qyauth.module')
      .then(m => m.QyauthModule)
    // children: [
    //   {
    //     path: '',
    //     component: LoginComponent,
    //     //NbLoginComponent
    //   },
    //   {
    //     path: 'login',
    //     component: LoginComponent,
    //   },
    //   {
    //     path: 'register',
    //     component: RegisterComponent,
    //   }
    // ],
  },
  { path: '', redirectTo: 'pages', pathMatch: 'full' },
  { path: '**', redirectTo: 'pages' },
];

const config: ExtraOptions = {
  useHash: false,
};

@NgModule({
  imports: [RouterModule.forRoot(routes, config)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
