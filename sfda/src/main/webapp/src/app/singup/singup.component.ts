import { SignupService } from './signup.service';
import { IUser } from './../shared/IUser';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators, ValidatorFn, ValidationErrors } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Config } from '../shared/Config';
import { Singup } from './singup';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.css']
})
export class SingupComponent implements OnInit {

  signUpForm = new FormGroup({
    TYPE: new FormControl('',[Validators.required]),
    firstName: new FormControl('', [Validators.required]),
    middleName: new FormControl(''),
    lastName: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
    password2: new FormControl('')

  }, {validators: passwordValidator});
  get firstName() {return this.signUpForm.get("firstName");}
  get lastName() {return this.signUpForm.get("lastName");}
  get phone() {return this.signUpForm.get("phone");}
  get email() {return this.signUpForm.get("email");}
  get password() {return this.signUpForm.get("password");}
  get password2() {return this.signUpForm.get("password2");}

  onSubmit(newUser: any) {

    this.http.signUp(newUser)
    .subscribe((user: Singup) => {
        console.log(user);
    }, (error) => {
      console.log(error);
    });
  }

  constructor(private http: SignupService, private config: Config) { }
  ngOnInit(): void {
  }

}

export const passwordValidator: ValidatorFn = (control: FormGroup): ValidationErrors | null => {
  const password = control.get('password');
  const password2 = control.get('password2');
  return password && password2 && password.value !== password2.value ? { identityRevealed: true } : null;
};
