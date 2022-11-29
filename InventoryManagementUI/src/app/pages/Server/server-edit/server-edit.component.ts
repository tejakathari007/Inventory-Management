import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServerService } from '../server.service';
import { Server } from '../server';
import { map, switchMap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-server-edit',
  templateUrl: './server-edit.component.html'
})
export class ServerEditComponent implements OnInit {

  id!: string;
  server!: Server;
  feedback: any = {};
 availableDeviceList: any;
 availableBrowserList: any;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private serverService: ServerService) {
  }

  getEnumData(type: any): any {
    if(this.serverService && this.serverService.enumsList) {
      return this.serverService.enumsList['Server'][type];
    }
    return  [];
  }

  getDeviceList(): any {
    if(this.serverService && this.serverService.deviceList) {
      let finalList =  JSON.parse(JSON.stringify(this.serverService.deviceList));
      if(!this.server.devices){
        this.server.devices = [];
      }
        this.server.devices.forEach(element => {
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
      if(this.server.browsers){
        this.server.browsers.forEach(element => {
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

  ngOnInit() {
    this.serverService.loadEnums();
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
            return of(new Server());
          }
          return this.serverService.findById(id);
        })
      )
      .subscribe({
        next: server => {
          this.server = server;
          this.feedback = {};
          const type = this.route.snapshot.paramMap.get('type');
          if(type=='duplicate'){
            this.server.id = null;
            this.server.name = null;
            this.server.serialNo = null;
            this.server.devices = [];
            this.server.browsers = [];
          }
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
    this.server.selectiondevices.forEach(element => {
      const copyobj = this.serverService.deviceList.findIndex(x=>x.id==element);
      if(!this.server.devices){
        this.server.devices = [];
      }

      const existingIndex =  this.server.devices.findIndex(x=>x.id==element);
      if(existingIndex==-1 && copyobj!=-1 ){
        if(!this.server.devices){
          this.server.devices = [];
        }
        this.server.devices.push(this.serverService.deviceList[copyobj]);
      }
    });
    this.availableDeviceList = this.getDeviceList();
    this.availableBrowserList = this.getBrowsersList();
  }
  addSelectedIntoBrowserList() {
    this.server.selectionbrowsers.forEach(element => {
      const copyobj = this.serverService.browserList.findIndex(x=>x.id==element);
      if(!this.server.browsers){
        this.server.browsers = [];
      }
      const existingIndex =  this.server.browsers.findIndex(x=>x.id==element);
      if(existingIndex==-1 && copyobj!=-1 ){
        if(!this.server.browsers){
          this.server.browsers = [];
        }
        this.server.browsers.push(this.serverService.browserList[copyobj]);
      }
    });
  }
  save() {

    this.serverService.save(this.server).subscribe({
      next: server => {
        this.server = server;
        this.feedback = {type: 'success', message: 'Save was successful!'};
        setTimeout(async () => {
          await this.router.navigate(['pages/server/servers']);
        }, 1000);
      },
      error: err => {
        this.feedback = {type: 'warning', message: 'Error saving'};
      }
    });
  }

  async cancel() {
    await this.router.navigate(['/pages/server/servers']);
  }
}
