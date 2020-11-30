import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'WebApp';
  url = "http://localhost:8080/api/get_qr?email=Juan@Valladares.com";
  // get IsLoggedIn(): Boolean {
  //   return localStorage.getItem('token')!= '' ;
  // }
  get IsLoggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }

}
