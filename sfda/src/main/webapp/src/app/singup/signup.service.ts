import { Config } from '../shared/Config';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IUser } from '../shared/IUser';
@Injectable({
  providedIn: 'root'
})
export class SignupService {
  public signUp(user: IUser ): Observable<IUser> {
    const body = JSON.stringify( user);
    const headers = new Headers({ 'Content-Type': 'application/json'});
    const options = {body, Headers: headers};
    return this.http.post<IUser>(this.config.signupUrl, options);

     }
  constructor(private http: HttpClient, private config: Config) { }
}
