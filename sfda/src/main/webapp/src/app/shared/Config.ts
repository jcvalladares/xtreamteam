import { Injectable } from '@angular/core';
Injectable();
export class Config
{
  public serverUrl = 'http://localhost:8080/';
  //public serverUrl = '/';
  public loginUrl = this.serverUrl + 'api/login/login';
  public signupUrl = this.serverUrl + 'api/login/signup';
}
