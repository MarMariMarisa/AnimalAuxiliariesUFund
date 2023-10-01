import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';

platformBrowserDynamic()
  .bootstrapModule(AppModule)
  .catch((err) => console.error(err));

setTimeout(() => {
  const fundButton = document.getElementById('fundButton');
  if (fundButton) {
    fundButton.addEventListener('click', (e) => {
      let funding = <HTMLInputElement>(
        document.getElementById('fundingRecipient')
      );
      if (funding) {
        let target = `isFunded${funding.value}`;
        const isNeedFunded = <HTMLElement>(
          document.getElementsByClassName(target)[0]
        );
        if (isNeedFunded) {
          isNeedFunded.classList.toggle('unsuccessful');
          isNeedFunded.classList.toggle('successful');

          if (isNeedFunded.classList.contains('successful')) {
            isNeedFunded.textContent = 'Funded';
          } else {
            isNeedFunded.textContent = 'Not Funded!';
          }
        }
      }
    });
  }
}, 500);
