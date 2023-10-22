import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NeedsFormComponent } from './needs-form/needs-form.component';
import { ManagerComponent } from './manager/manager.component';

@NgModule({
  declarations: [AppComponent, NeedsFormComponent, ManagerComponent],
  imports: [BrowserModule, AppRoutingModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
