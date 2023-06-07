import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  name: string;
  password: string;

  constructor(private userService: UserService, private router: Router) {}

  login(): void {
    // Validate the login credentials
    if (this.name && this.password) {
      // Call the login method in the UserService passing the credentials
      this.userService.getUserCredentials(this.name, this.password).subscribe(
        (response) => {
          console.log('User logged in successfully:', response);
          this.userService.setUserId(response.id)
          const useridstring = response.id.toString();
          localStorage.setItem('userId', useridstring);
          console.log(useridstring);
          this.router.navigate(['/dialog']);
        },
        (error) => {
          console.error('Failed to log in:', error);
          // Optionally, handle the error response here
        }
      );
    } else {
      // Handle invalid or empty credentials
      console.error('Invalid credentials');
    }
  }
}
