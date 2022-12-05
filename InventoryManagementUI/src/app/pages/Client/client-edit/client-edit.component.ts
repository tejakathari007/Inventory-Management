import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../client.service';
import { Client } from '../client';
import { map, switchMap } from 'rxjs/operators';
import { of } from 'rxjs';
import { ServerService } from '../../Server/server.service';

@Component({
  selector: 'app-client-edit',
  templateUrl: './client-edit.component.html'
})
export class ClientEditComponent implements OnInit {

  id!: string;
  client!: Client;
  feedback: any = {};

  availableDeviceList: any;
  availableBrowserList: any;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService,
    private serverService: ServerService) {
  }

  ngOnInit() {
    this.serverService.loadDevice();
    this.serverService.loadBrowsers();
    this
      .route
      .params
      .pipe(
        map(p => p['id']),
        switchMap(id => {
          if (id === 'new') { 
            setTimeout(() => {
              this.availableDeviceList = this.getDeviceList();
              this.availableBrowserList = this.getBrowsersList();
            }, 1000);
            return of(new Client()); }
          return this.clientService.findById(id);
        })
      )
      .subscribe({
        next: client => {
          this.client = client;
          this.feedback = {};
          setTimeout(() => {
            this.availableDeviceList = this.getDeviceList();
            this.availableBrowserList = this.getBrowsersList();
          }, 500);
        },
        error: err => {
          this.feedback = {type: 'warning', message: 'Error loading'};
        }
      });
  }
  addSelectedIntoDeviceList() {
    this.client.selectiondevices.forEach(element => {
      const copyobj = this.serverService.deviceList.findIndex(x=>x.id==element);
      if(!this.client.devices){
        this.client.devices = [];
      }

      const existingIndex =  this.client.devices.findIndex(x=>x.id==element);
      if(existingIndex==-1 && copyobj!=-1 ){
        if(!this.client.devices){
          this.client.devices = [];
        }
        this.client.devices.push(this.serverService.deviceList[copyobj]);
      }
    });
    this.availableDeviceList = this.getDeviceList();
    this.availableBrowserList = this.getBrowsersList();
  }
  addSelectedIntoBrowserList() {
    this.client.selectionbrowsers.forEach(element => {
      const copyobj = this.serverService.browserList.findIndex(x=>x.id==element);
      if(!this.client.browsers){
        this.client.browsers = [];
      }
      const existingIndex =  this.client.browsers.findIndex(x=>x.id==element);
      if(existingIndex==-1 && copyobj!=-1 ){
        if(!this.client.browsers){
          this.client.browsers = [];
        }
        this.client.browsers.push(this.serverService.browserList[copyobj]);
      }
    });
  }
  getDeviceList(): any {
    if(this.serverService && this.serverService.deviceList) {
      let finalList =  JSON.parse(JSON.stringify(this.serverService.deviceList));
      if(!this.client.devices){
        this.client.devices = [];
      }
        this.client.devices.forEach(element => {
          const inx = finalList.findIndex(x=>x.id==element.id);
          if(inx != -1){
            finalList.splice(inx, 1);
          }
        });
      return finalList;
    }
    return  [];
  }
  removeFromList(list,item){
    if(list && item){
      const inx = list.findIndex(x=>x.id == item.id);
      if(inx!=-1){
        list.splice(inx,1);
      }
    }
    this.availableDeviceList = this.getDeviceList();
    this.availableBrowserList = this.getBrowsersList();
  }

  getBrowsersList(){
    if(this.serverService && this.serverService.browserList) {
      let finalList =  JSON.parse(JSON.stringify(this.serverService.browserList));
      if(this.client.browsers){
        this.client.browsers.forEach(element => {
          const inx = finalList.findIndex(x=>x.id==element.id);
          if(inx != -1){
            finalList.splice(inx, 1);
          }
        });
      }
      return finalList;
    }
    return  [];
  }
  save() {
    this.clientService.save(this.client).subscribe({
      next: client => {
        this.client = client;
        this.feedback = {type: 'success', message: 'Save was successful!'};
        setTimeout(async () => {
          await this.router.navigate(['/pages/client/clients']);
        }, 1000);
      },
      error: err => {
        this.feedback = {type: 'warning', message: 'Error saving'};
      }
    });
  }

  async cancel() {
    await this.router.navigate(['/pages/client/clients']);
  }
}
