import { Component } from '@angular/core';

import { MENU_ITEMS } from './pages-menu';

@Component({
  selector: 'ngx-pages',
  styleUrls: ['pages.component.scss'],
  template: `
    <ngx-one-column-layout>
      <nb-menu [items]="menu"></nb-menu>
      <router-outlet></router-outlet>
    </ngx-one-column-layout>
  `,
})
export class PagesComponent {
  menu = MENU_ITEMS;
constructor(){
var cont = JSON.parse(localStorage.getItem('userDetails')); 
if(cont.role =='ROLE_EDITOR'){
  this.menu = this.menu.filter(x=>x.title!='Audit');
}
}
 
}
