import { Config } from './../shared/Config';
import { Component, OnInit } from '@angular/core';
import {IUser} from '../shared/IUser';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';


@Component({
  selector: 'app-qrcode',
  templateUrl: './qrcode.component.html',
  styleUrls: ['./qrcode.component.css']
})
export class QrcodeComponent implements OnInit {

  constructor(private config: Config) { }
  FullName: string;
  userType: string;
  email: string;

  get url() {

    let userStored = localStorage.getItem("user");

   //   localStorage.removeItem('token');
   //   return;

   let user:IUser = JSON.parse(userStored);
   this.FullName = user.firstName + ' ' + user.lastName;
   switch(user.type)
   {
     case "Donor":
       this.userType = "Donor";
       break;
     case "NOG":
       this.userType = "Non-Profit Organization";
       break;
    case "FAMILY":
      this.userType = "Family";
      break;
   }

  return this.config.getQrCodeUrl +"email="+ user.email;
   // if (this.user)
   // {
   //   this.url = config.getQrCodeUrl + this.user.email;
   //   this.username = this.user.firstName + " " + this.user.lastName;
   //   this.usertype = this.user.TYPE;
   //   this.email = this.user.email;
   // }
};
  ngOnInit(): void {
  }

}
