import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from './register/register.component';
import { QyauthComponent } from './qyauth/qyauth.component';


const routes: Routes = [{
  path: '',
  component: QyauthComponent,
  children: [
    {
        path: 'login',
        component: LoginComponent,
        // NbLoginComponent
      },
      {
        path: 'register',
        component: RegisterComponent,
      },
    {
      path: '',
      redirectTo: 'login',
      pathMatch: 'full',
    }
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class qyauthRoutingModule {
}
