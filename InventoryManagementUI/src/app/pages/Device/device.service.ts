import { Device } from './device';
import { DeviceFilter } from './device-filter';
import { Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';

const headers = new HttpHeaders().set('Accept', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  deviceList: Device[] = [];
  odeviceList: Device[] = [];
  api = environment.baseURL;
  deviceurl = environment.deviceURL;
  enumsList: any;
  constructor(private http: HttpClient) {
  }

  findById(id: string): Observable<Device> {
    const url = `${this.api}` + '/getDevice/' + id;
    return this.http.get<Device>(url, { headers});
  }

  load(filter: DeviceFilter): void {
    this.find(filter).subscribe({
      next: result => {
        this.deviceList = result;
        this.odeviceList = result;
      },
      error: err => {
        console.error('error loading', err);
      }
    });
  }

  searchfilter(filter: DeviceFilter): void {
    if(filter.name){
    this.deviceList =  this.odeviceList.filter(x=> (x.name? x.name.toLowerCase().includes(filter.name.toLowerCase()):true));
    } else {
      this.deviceList =  this.odeviceList;
    }

  }
  find(filter: DeviceFilter): Observable<Device[]> {
    return this.http.get<Device[]>(this.api + '/getAlldevices', { headers});
  }

  save(entity: Device): Observable<Device> {
    let url = '';
    if (entity.id) {
      url = `${this.api}`;
      return this.http.put<Device>(url + '/updateDevice', entity, {headers});
    } else {
      url = `${this.api}`;
      return this.http.post<Device>(url + '/addDevice', entity, {headers});
    }
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
  getQr(): Observable<any> {
    return this.http.get<any>(this.api + '/uniqueURL/generate', {headers});
  }

  delete(entity: Device): Observable<Device> {
    let url = '';
    if (entity.id) {
      url = `${this.api}` + '/deleteDevice/' + `${entity.id.toString()}`;
      return this.http.delete<Device>(url, {headers});
    }
    return EMPTY;
  }
  getTheDeviceInfo(uniqueId): Observable<any> {
    let url = '';
    if (uniqueId) {
      url = `${this.deviceurl}` + `${uniqueId.toString()}`;
      return this.http.get<Device>(url, {headers});
    }
    return EMPTY;
  }
  getDeviceList(): Observable<any>
  {
    let url = `${this.api}` + '/getDeviceList';
      return this.http.get<Device>(url, {headers});
    return EMPTY;
  }
}
