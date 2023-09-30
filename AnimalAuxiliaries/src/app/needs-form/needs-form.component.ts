import { Component } from '@angular/core';

@Component({
  selector: 'app-needs-form',
  templateUrl: './needs-form.component.html',
  styleUrls: ['./needs-form.component.css'],
})
export class NeedsFormComponent {
  needsData: any = {};

  onSubmit() {
    console.log(this.needsData);
    const needsSection = document.getElementById('needs');
    if (needsSection) {
      let current = needsSection.innerHTML;
      current += `
      <div class="needItem">
        <h2 class="name">${this.needsData.name}</h2>
        <p class="type">${this.needsData.type}</p>
        <p class="description">${this.needsData.description}</p>
        <p class="amount">Amount Required: $${this.needsData.amount}</p>
        <div class="isFunded${
          document.querySelectorAll('.needItem').length + 1
        } notFunded"></div>
      </div>
      `;
      needsSection.innerHTML = current;
    }
  }
}
