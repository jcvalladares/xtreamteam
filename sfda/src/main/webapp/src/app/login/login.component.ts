import { IUser } from './../shared/IUser';
import { LoginService } from './login.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
declare var $: any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  private token: IUser;
  get IsLoggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }

  public errorMessage = '';

  loginForm = new FormGroup({
    login: new FormControl(''),
    password: new FormControl('')
  });

  LogOut() {
    if (this.IsLoggedIn) {
      localStorage.removeItem('token');
    }
  }
  /**
   * Login
   *
   */
  // tslint:disable-next-line: typedef
  onSubmit(user) {
      localStorage.removeItem('token');
      this.errorMessage = '';
      this.loginServices.Login(user.login, user.password)
        .subscribe((token: IUser) => {
          // this.token = token;
          localStorage.setItem('token', '1');
        }, (error) => {
          localStorage.removeItem('token');
          this.errorMessage = 'User and Password Incorrect';
        });
    //}
  }

  constructor(private loginServices: LoginService) { }

  ngOnInit(): void {
  }

}
