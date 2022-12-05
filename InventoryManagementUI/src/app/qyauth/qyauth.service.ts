import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

const headers = new HttpHeaders().set('Accept', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class QyauthService {
  api = environment.baseURLAuth;
  baseURL = environment.baseURL;
  constructor(private http: HttpClient) { }
  login(loginData: any): Observable<any> {
    const url = `${this.api}` + '/auth/login';
   // return this.http.post<Device>(url + '/addDevice', entity, {headers});
    return this.http.post<any>(url, loginData ,{ headers });
  }
  register(loginData: any): Observable<any> {
    const url = `${this.baseURL}` + '/user';
   // return this.http.post<Device>(url + '/addDevice', entity, {headers});
    return this.http.post<any>(url, loginData ,{ headers });
  }
}
