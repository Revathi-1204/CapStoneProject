import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string;
  password: string;
  loginError: boolean = false;
  constructor(private router: Router) {}
 
  login() {
    // Perform authentication logic (e.g., sending a request to a server).
    // If authentication is successful, navigate to the 'plans' route.
    if (this.username.trim() === 'admin' && this.password.trim() === 'admin') {
      this.router.navigate(['/dashboard']);
    } else {
      console.log('Login failed');
      this.loginError = true;
    }
  }
}
