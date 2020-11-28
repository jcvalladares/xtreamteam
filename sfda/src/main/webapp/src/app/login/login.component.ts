import { IUser } from './../shared/IUser';
import { LoginService } from './login.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  private token: IUser;
  get IsLoggedIn(): boolean {
    return localStorage.getItem('token') != '';
  }

  loginForm = new FormGroup({
    login: new FormControl(''),
    password: new FormControl('')
  });

  /**
   * Login
   *
   */
  // tslint:disable-next-line: typedef
  onSubmit(customerData) {
    const email = this.loginForm.controls.login.value;
    const password = this.loginForm.controls.password.value;
    localStorage.removeItem('token');
    this.loginServices.Login(email, password)
    .subscribe((token: IUser) => {
      this.token = token;
    }, (error) => {
      localStorage.setItem('token2', '1234');
    });

  }
  constructor(private loginServices: LoginService) { }

  ngOnInit(): void {
  }

}
