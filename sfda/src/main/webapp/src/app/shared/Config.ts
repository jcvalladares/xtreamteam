import { Injectable } from '@angular/core';
Injectable();
export class Config
{
//  public serverUrl = 'http://54.219.4.96:8080/';
  public serverUrl = 'http://localhost:8080/';
  public loginUrl = this.serverUrl + 'api/login/login';
  public signupUrl = this.serverUrl + 'api/user';
  public getQrCodeUrl = this.serverUrl + 'api/get_qr?email=test@test.com'; // todo: fix it
}
