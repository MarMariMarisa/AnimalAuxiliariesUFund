import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NeedsFormComponent } from './needs-form/needs-form.component';
import { ManagerComponent } from './manager/manager.component';
import { HelperComponent } from './helper/helper.component';
import { LoginComponent } from './login/login.component';
import { BasketComponent } from './basket/basket.component';
import { NeedComponent } from './need/need.component';

@NgModule({
  declarations: [AppComponent, NeedsFormComponent, ManagerComponent, HelperComponent, LoginComponent, BasketComponent, NeedComponent],
  imports: [BrowserModule, AppRoutingModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
