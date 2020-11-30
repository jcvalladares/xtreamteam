import { Config } from '../shared/Config';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IUser } from '../shared/IUser';

@Injectable({
  providedIn: 'root'
})

@Injectable()
export class LoginService {
 public Login(email: string, password: string): Observable<IUser> {
    const body = JSON.stringify({ email, password });
    const headers = new Headers({ 'Content-Type': 'application/json'});
    const options = {body, Headers: headers};
    return this.http.post<IUser>(this.config.loginUrl + '?email=' + email + '&password=' + password, options);
     }
  constructor(private http: HttpClient, private config: Config) { }
}
