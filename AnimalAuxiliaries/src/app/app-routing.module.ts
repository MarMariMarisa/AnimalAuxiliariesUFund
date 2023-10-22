import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManagerComponent } from './manager/manager.component';
import { HelperComponent } from './helper/helper.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: 'manager', component: ManagerComponent },
  { path: 'helper', component: HelperComponent },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
