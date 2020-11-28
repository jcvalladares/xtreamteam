import { Config } from '../shared/Config';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IUser } from '../shared/IUser';

@Injectable({
  providedIn: 'root'
})

@Injectable()
export class LoginService{

  public Login(email: string, password: string): Observable<IUser> {
    let body = JSON.stringify({ 'email': email, "password": password });
    let headers = new Headers({ 'Content-Type': 'application/json'});
    let options = {body: body, Headers: headers};
    return this.http.post<IUser>(this.config.loginUrl, options);

     }
  constructor(private http: HttpClient, private config: Config) { }
}


