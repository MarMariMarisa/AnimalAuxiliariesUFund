import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'AnimalAuxiliaries';
}
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
