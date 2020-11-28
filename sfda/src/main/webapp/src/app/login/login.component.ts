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
    let email = this.loginForm.controls.login.value;
    let password = this.loginForm.controls.password.value;;
    this.loginServices.Login(email, password).subscribe((token: IUser) => {
      this.token = token;

    }

    );

  }
  constructor(private loginServices: LoginService) { }

  ngOnInit(): void {
  }

}
