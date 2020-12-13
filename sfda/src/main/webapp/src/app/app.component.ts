import { IUser } from './shared/IUser';
import { Config } from './shared/Config';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'WebApp';

  get IsLoggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }
  constructor(private config: Config){ }
  
}
