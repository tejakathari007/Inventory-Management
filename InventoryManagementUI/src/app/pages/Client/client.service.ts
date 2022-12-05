import { Client } from './client';
import { ClientFilter } from './client-filter';
import { Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';

const headers = new HttpHeaders().set('Accept', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  clientList: Client[] = [];
  oclientList: Client[] = [];
  api = environment.baseURL;

  constructor(private http: HttpClient) {
  }

  findById(id: string): Observable<Client> {
    const url = `${this.api}`+'/getClient' +`/${id}`;
    return this.http.get<Client>(url, { headers});
  }
  searchfilter(filter: ClientFilter): void {
    if(filter.name){
    this.clientList =  this.oclientList.filter(x=> (x.name? x.name.toLowerCase().includes(filter.name.toLowerCase()):true));
    } else {
      this.clientList =  this.oclientList;
    }

  }
  load(filter: ClientFilter): void {
    this.find(filter).subscribe({
      next: result => {
        this.clientList = result;
        this.oclientList = result;
      },
      error: err => {
        console.error('error loading', err);
      }
    });
  }

  auditData(): Observable<any> {

    return this.http.get<any>(this.api+'/audits', {headers});
  }


  find(filter: ClientFilter): Observable<Client[]> {

    return this.http.get<Client[]>(this.api+'/getAllClients', {headers});
  }

  save(entity: Client): Observable<Client> {
    let url = '';
    if (entity.id) {
      url = `${this.api}` + '/updateClient';
      return this.http.put<Client>(url, entity, {headers});
    } else {
      url = `${this.api}` + '/addClient';
      return this.http.post<Client>(url, entity, {headers});
    }
  }

  delete(entity: Client): Observable<Client> {
    let url = '';
    if (entity.id) {
      url = `${this.api}` + '/deleteClient/' + `${entity.id.toString()}`;
      return this.http.delete<Client>(url, {headers});
    }
    return EMPTY;
  }
  sendMail(id: string):  Observable<any> {
    let obj = {
      "clientId" : id
    };
    if (id) {
      let url = `${this.api}` + '/triggerMail' ;
      return this.http.post<any>(url,obj, {headers});
    }
    return EMPTY;
  }
}

