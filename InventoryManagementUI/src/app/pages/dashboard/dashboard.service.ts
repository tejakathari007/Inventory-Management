import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

const headers = new HttpHeaders().set('Accept', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  api = environment.baseURL;
  constructor(private http: HttpClient) { }
  serverState(): Observable<any> {
    const url = `${this.api}` + '/getAllServerCountByState';
    return this.http.get<any>(url, { headers});
  }
  serverregion(): Observable<any> {
    const url = `${this.api}` + '/getAllServerCountPerRegion';
    return this.http.get<any>(url, { headers});
  }
  serveros(): Observable<any> {
    const url = `${this.api}` + '/getAllServerCountByOS';
    return this.http.get<any>(url, { headers});
  }

  serverhosting(): Observable<any> {
    const url = `${this.api}` + '/getAllServerCountByHostingType';
    return this.http.get<any>(url, { headers});
  }

  serverDeviceCountPer(): Observable<any> {
    const url = `${this.api}` + '/getAvgDeviceCountPerServer';
    return this.http.get<any>(url, { headers});
  }
  serverBrowserCountPer(): Observable<any> {
    const url = `${this.api}` + '/getAvgBrowserCountPerServer';
    return this.http.get<any>(url, { headers});
  }
  serverAllServers(): Observable<any> {
    const url = `${this.api}` + '/getAllServersCount';
    return this.http.get<any>(url, { headers});
  }

  DeviceCountPer(): Observable<any> {
    const url = `${this.api}` + '/getAllDevicesCount';
    return this.http.get<any>(url, { headers});
  }
  deviceState(): Observable<any> {
    const url = `${this.api}` + '/getAllDevicesCountByState';
    return this.http.get<any>(url, { headers});
  }
  devicemaker(): Observable<any> {
    const url = `${this.api}` + '/getAllDevicesCountByMakerAndModel';
    return this.http.get<any>(url, { headers});
  }
  deviceos(): Observable<any> {
    const url = `${this.api}` + '/getAllDevicesCountByOS';
    return this.http.get<any>(url, { headers});
  }

  browserCountPer(): Observable<any> {
    const url = `${this.api}` + '/getAllBrowsersCount';
    return this.http.get<any>(url, { headers});
  }
  browserState(): Observable<any> {
    const url = `${this.api}` + '/getAllBrowsersCountByState';
    return this.http.get<any>(url, { headers});
  }
  browsermaker(): Observable<any> {
    const url = `${this.api}` + '/getAllBrowsersCountByNameAndVersion';
    return this.http.get<any>(url, { headers});
  }
  browserothers(): Observable<any> {
    const url = `${this.api}` + '/getAllOtherDevicesCountByType';
    return this.http.get<any>(url, { headers});
  }

  clientCountPer(): Observable<any> {
    const url = `${this.api}` + '/getAllClientsCount';
    return this.http.get<any>(url, { headers});
  }
  clientState(): Observable<any> {
    const url = `${this.api}` + '/getBrowserCountByClient';
    return this.http.get<any>(url, { headers});
  }
  clientmaker(): Observable<any> {
    const url = `${this.api}` + '/getDeviceCountByClient';
    return this.http.get<any>(url, { headers});
  }
  getReport(): Observable<any> {
    const url = `${this.api}` + '/getReport';
    return this.http.get<any>(url, { headers});
  }

}
