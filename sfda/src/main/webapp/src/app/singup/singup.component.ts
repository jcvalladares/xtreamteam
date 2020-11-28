import { IUser } from './../shared/IUser';

import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.css']
})
export class SingupComponent implements OnInit {

  constructor() { }
  signUpForm = new FormGroup({
    firstName: new FormControl(''),
    middleName: new FormControl(''),
    lastName: new FormControl(''),
    Id: new FormControl(''),
    Phone: new FormControl('')
  });

  onSubmit(customerData) {
    const firstName = this.signUpForm.controls.firstName.value;
    const middleName = this.signUpForm.controls.middleName.value;
    const lastName = this.signUpForm.controls.lastName.value;
    const id = this.signUpForm.controls.Id.value;
    const phone = this.signUpForm.controls.Phone.value;

    // this.loginServices.Login(email, password)
    // .subscribe((token: IUser) => {
    //   this.token = token;
    // }, (error) => {
    //   localStorage.setItem('token2', '1234');
    // });

  }
  ngOnInit(): void {
  }

}
