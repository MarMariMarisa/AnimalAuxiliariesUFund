import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NeedsFormComponent } from './needs-form/needs-form.component';

@NgModule({
  declarations: [AppComponent, NeedsFormComponent],
  imports: [BrowserModule, AppRoutingModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
const fundButton = document.getElementById('fundButton');
if (fundButton) {
  fundButton.addEventListener('onclick', (e) => {
    console.log('hello');
    let funding = <HTMLInputElement>document.getElementById('fundingRecipient');
    if (funding) {
      let target = `isFunded${funding.value}`;
      const isNeedFunded = document.getElementById(target);
      if (isNeedFunded) {
        isNeedFunded.classList.toggle('successful');
        isNeedFunded.classList.toggle('unsuccessful');
        if (isNeedFunded.classList.contains('successful')) {
          isNeedFunded.textContent = 'Funded!';
        } else {
          isNeedFunded.textContent = 'Not Funded!';
        }
      }
    }
  });
}
