import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DeviceService } from '../device.service';
import { Device } from '../device';
import { map, switchMap } from 'rxjs/operators';
import { of } from 'rxjs';
import { ClientService } from '../../Client/client.service';
import { ServerService } from '../../Server/server.service';

@Component({
  selector: 'app-device-edit',
  templateUrl: './device-edit.component.html'
})
export class DeviceEditComponent implements AfterViewInit , OnInit {

  id!: string;
  device!: Device;
  feedback: any = {};
  severList = [];
  clientList = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private deviceService: DeviceService,
    private clientService: ClientService,
    private serverService: ServerService) {
  }

  ngOnInit() {
    this
      .route
      .params
      .pipe(
        map(p => p['id']),
        switchMap(id => {
          if (id === 'new') {
            return of(new Device());
           }
          return this.deviceService.findById(id);
        })
      )
      .subscribe({
        next: device => {
          this.device = device;
          this.feedback = {};
          if(!device.id) {
            var data = JSON.parse(localStorage.getItem('scanDat'));
            if(data) {
            localStorage.removeItem('scanDat');
            this.device.model = data.model;
            this.device.os = data.os;
            this.device.maker = data.maker;
            }
          }
        },
        error: err => {
          this.feedback = {type: 'warning', message: 'Error loading'};
        }
      });
      this.deviceService.loadEnums();
  }

  getEnumData(type: any): any {
    if(this.deviceService && this.deviceService.enumsList) {
      return this.deviceService.enumsList['Device'][type];
    }
return  [];
  }
  save() {
    this.device.deviceType = 'MOBILE';
    if(this.device.state!=='LIVE') {
      this.device.status = 'NA';
    }
    this.deviceService.save(this.device).subscribe({
      next: device => {
        this.device = device;
        this.feedback = {type: 'success', message: 'Save was successful!'};
        setTimeout(async () => {
          await this.router.navigate(['/pages/device/devices']);
        }, 1000);
      },
      error: err => {
        this.feedback = {type: 'warning', message: 'Error saving'};
      }
    });
  }

  async cancel() {
    await this.router.navigate(['/pages/device/devices']);
  }
    load(): void {
    const filter:any = {};
    this.serverService.load(filter);
    this.clientService.load(filter);
  }

    ngAfterViewInit(): void {
    //Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
    //Add 'implements AfterViewInit' to the class.
    this.load();
    }
      getseverList(){
    return this.serverService.serverList;
  }

  getclientList(){
    return this.clientService.clientList;
  }
    compare(val1, val2) {
    if(val1 && val2)
      return val1.id === val2.id;
    return false;
  }
  compareserver(val1, val2) {
    if(val1 && val2)
    return val1.id === val2.id;
  return false;
  }
}
