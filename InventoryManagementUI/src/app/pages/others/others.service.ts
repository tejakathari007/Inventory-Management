import { Others } from './others';
import { OthersFilter } from './others-filter';
import { Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';

const headers = new HttpHeaders().set('Accept', 'application/json');

@Injectable()
export class OthersService {
  othersList: Others[] = [];
  oothersList: Others[] = [];
  api =  environment.baseURL;

  constructor(private http: HttpClient) {
  }

  findById(id: string): Observable<Others> {
    const url = `${this.api}` + '/getOtherDevices' + `/${id}`;
    return this.http.get<Others>(url, { headers});
  }

  load(filter: OthersFilter): void {
    this.find(filter).subscribe({
      next: result => {
        this.othersList = result;
      },
      error: err => {
        console.error('error loading', err);
      }
    });
  }

  find(filter: OthersFilter): Observable<Others[]> {
    return this.http.get<Others[]>(this.api + '/getAllOtherDevices', { headers});
  }

  save(entity: Others): Observable<Others> {
    let url = '';
    if (entity.id) {
      url = `${this.api}` + '/updateOtherDevices';
      return this.http.put<Others>(url, entity, {headers});
    } else {
      url = `${this.api}` + '/addOtherDevices';
      return this.http.post<Others>(url, entity, {headers});
    }
  }
  searchfilter(filter: OthersFilter): void {
    if(filter.name){
    this.othersList =  this.oothersList.filter(x=> (x.name? x.name.toLowerCase().includes(filter.name.toLowerCase()):true));
    } else {
      this.othersList =  this.oothersList;
    }

  }
  delete(entity: Others): Observable<Others> {
    let url = '';
    if (entity.id) {
      url = `${this.api}` + '/deleteOtherDevices/' + `${entity.id.toString()}`;
      return this.http.delete<Others>(url, {headers});
    }
    return EMPTY;
  }
}

