import { Injectable } from '@angular/core';
Injectable();
export class Config
{
  public serverUrl = 'http://54.219.4.96:8080/';
  //public serverUrl = '/';
  public loginUrl = this.serverUrl + 'api/login/login';
  public signupUrl = this.serverUrl + 'api/login/signup';
  public getQrCodeUrl = this.serverUrl + 'get_qr?email=test@test.com'; // todo: fix it
}
