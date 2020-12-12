import { Singup } from './singup';
import { Config } from '../shared/Config';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IUser } from '../shared/IUser';
@Injectable({
  providedIn: 'root'
})
export class SignupService {
  public signUp(user: Singup ): Observable<Singup> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    return this.http.post<Singup>(this.config.signupUrl, user,  httpOptions);
     }
  constructor(private http: HttpClient, private config: Config) { }
}
