import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent {
  @Output() choiceSelected = new EventEmitter<string>();

  constructor(private router: Router) {}

  onChoiceSelected(choice: string): void {
    switch (choice) {
      case 'Option 1':
        this.router.navigate(['/meal-plan']);
        break;
      case 'Option 2':
        this.router.navigate(['/meal-plan-mantain']);
        break;
      case 'Option 3':
        this.router.navigate(['/meal-plan-gain']);
        break;
      default:
        break;
    }
  }
}
