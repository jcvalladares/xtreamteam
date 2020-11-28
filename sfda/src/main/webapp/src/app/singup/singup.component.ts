import { SignupService } from './signup.service';
import { IUser } from './../shared/IUser';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Config } from '../shared/Config';

@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.css']
})
export class SingupComponent implements OnInit {


  signUpForm = new FormGroup({
    firstName: new FormControl(''),
    middleName: new FormControl(''),
    lastName: new FormControl(''),
    Id: new FormControl(''),
    phone: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    password2: new FormControl('')
  });

  onSubmit(newUser: IUser) {
    this.http.signUp(newUser)
    .subscribe((token: IUser) => {

    }, (error) => {

    });

  }
  constructor(private http: SignupService, private config: Config) { }
  ngOnInit(): void {
  }

}
