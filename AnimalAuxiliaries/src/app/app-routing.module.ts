import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManagerComponent } from './manager/manager.component';
import { HelperComponent } from './helper/helper.component';
import { LoginComponent } from './login/login.component';
import { NeedComponent } from './need/need.component';
import { BasketComponent } from './basket/basket.component';

const routes: Routes = [
  { path: 'manager', component: ManagerComponent },
  { path: 'helper', component: HelperComponent },
  { path: 'manager/:id', component: NeedComponent },
  { path: 'login', component: LoginComponent },
  { path: 'basket', component: BasketComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
