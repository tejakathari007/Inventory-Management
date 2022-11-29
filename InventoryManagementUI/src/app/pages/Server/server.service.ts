import { Server } from './server';
import { ServerFilter } from './server-filter';
import { Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';

const headers = new HttpHeaders().set('Accept', 'application/json');

@Injectable()
export class ServerService {
  serverList: Server[] = [];
  oserverList: Server[] = [];
  enumsList: any;
  deviceList: any;
  browserList: any;
  api = environment.baseURL;

  constructor(private http: HttpClient) {
  }

  findById(id: string): Observable<Server> {
    const url = `${this.api + '/getServer'}/${id}`;
    const params = { id: id };
    return this.http.get<Server>(url, {headers});
  }
  searchfilter(filter: ServerFilter): void {
    if(filter.name){
    this.serverList =  this.oserverList.filter(x=> (x.name? x.name.toLowerCase().includes(filter.name.toLowerCase()):true));
    } else {
      this.serverList =  this.oserverList;
    }

  }

  load(filter: ServerFilter): void {
    this.find(filter).subscribe({
      next: result => {
        this.serverList = result;
        this.oserverList = result;
      },
      error: err => {
        console.error('error loading', err);
      }
    });
  }

  find(filter: ServerFilter): Observable<Server[]> {
    // const params = {
    //   'name': filter.name,
    // };
    return this.http.get<Server[]>(this.api + '/getAllServers', {headers});
  }

  loadEnums(){
    this.enums().subscribe({
      next: result => {
        this.enumsList = result;
      },
      error: err => {
        console.error('error loading', err);
      }
    });
  }
  enums(): Observable<any> {
    return this.http.get<any>(this.api + '/getAllConstants', {headers});
  }
  loadDevice(){
    this.availableDevice().subscribe({
      next: result => {
        this.deviceList = result;
      },
      error: err => {
        console.error('error loading', err);
      }
    });
  }
  availableDevice(): Observable<any> {
    return this.http.get<any>(this.api + '/getLiveDevices', {headers});
  }
  loadBrowsers(){
    this.availableBrowsers().subscribe({
      next: result => {
        this.browserList = result;
      },
      error: err => {
        console.error('error loading', err);
      }
    });
  }
  availableBrowsers(): Observable<any> {
    return this.http.get<any>(this.api + '/getAvailableBrowsers', {headers});
  }

  save(entity: Server): Observable<Server> {
    let url = '';
    if (entity.id) {
      url = `${this.api}` + '/updateServer';
      return this.http.put<Server>(url, entity, {headers});
    } else {
      url = `${this.api}` + '/addServer';
      return this.http.post<Server>(url, entity, {headers});
    }
  }

  delete(entity: Server): Observable<Server> {
    let url = '';
    if (entity.id) {
      url = `${this.api}` + '/deleteServer/' + `${entity.id.toString()}`;
      return this.http.delete<Server>(url, {headers});
    }
    return EMPTY;
  }
}

