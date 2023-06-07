import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  loggedIn: boolean = false;

  constructor(private router: Router) {}

  login(): void {
    // Perform login logic here
    this.loggedIn = true;
  }

  logout(): void {
    // Perform logout logic here
    this.loggedIn = false;
    this.router.navigate(['/']);
  }
}
