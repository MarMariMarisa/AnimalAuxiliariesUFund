import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ManagerComponent } from './manager/manager.component';
import { HelperComponent } from './helper/helper.component';
import { LoginComponent } from './login/login.component';
import { BasketComponent } from './basket/basket.component';
import { CupboardComponent } from './cupboard/cupboard.component';

import { HttpClientModule } from '@angular/common/http';
import { NeedComponent } from './need/need.component';
import { NeedSearchComponent } from './need-search/need-search.component';
import { FundingBasketComponent } from './funding-basket/funding-basket.component';
import { NavComponent } from './nav/nav.component';
import { AnalyticsComponent } from './analytics/analytics.component';
import { ManagerNavComponent } from './manager-nav/manager-nav.component';

@NgModule({
  declarations: [
    AppComponent,
    ManagerComponent,
    HelperComponent,
    LoginComponent,
    BasketComponent,
    CupboardComponent,
    NeedComponent,
    NeedComponent,
    NeedSearchComponent,
    FundingBasketComponent,
    NavComponent,
    AnalyticsComponent,
    ManagerNavComponent,
  ],
  imports: [BrowserModule, FormsModule, AppRoutingModule, HttpClientModule],
  bootstrap: [AppComponent],
})
export class AppModule {}
