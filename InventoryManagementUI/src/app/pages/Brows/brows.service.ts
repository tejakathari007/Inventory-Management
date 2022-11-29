import { Brows } from './brows';
import { BrowsFilter } from './brows-filter';
import { Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';

const headers = new HttpHeaders().set('Accept', 'application/json');

@Injectable()
export class BrowsService {
  browsList: Brows[] = [];
  api = environment.baseURL;

  constructor(private http: HttpClient) {
  }

  findById(id: string): Observable<Brows> {
    const url = `${this.api}` + '/getBrowser' + `/${id}`;
    return this.http.get<Brows>(url, { headers});
  }

  load(filter: BrowsFilter): void {
    this.find(filter).subscribe({
      next: result => {
        this.browsList = result;
      },
      error: err => {
        console.error('error loading', err);
      }
    });
  }

  find(filter: BrowsFilter): Observable<Brows[]> {
    return this.http.get<Brows[]>(this.api + '/getAllBrowsers', { headers});
  }

  save(entity: Brows): Observable<Brows> {
    let url = '';
    if (entity.id) {
      url = `${this.api}` + '/updateBrowser';
      return this.http.put<Brows>(url, entity, {headers});
    } else {
      url = `${this.api}` + '/addBrowser';
      return this.http.post<Brows>(url, entity, {headers});
    }
  }

  delete(entity: Brows): Observable<Brows> {
    let url = '';
    if (entity.id) {
      url = `${this.api}` + '/deleteBrowser/' + `${entity.id.toString()}`;
      return this.http.delete<Brows>(url, {headers});
    }
    return EMPTY;
  }
}

