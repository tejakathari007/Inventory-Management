import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DeviceService } from '../device.service';
import { Device } from '../device';
import { map, switchMap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-device-edit',
  templateUrl: './device-edit.component.html'
})
export class DeviceEditComponent implements OnInit {

  id!: string;
  device!: Device;
  feedback: any = {};

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private deviceService: DeviceService) {
  }

  ngOnInit() {
    this
      .route
      .params
      .pipe(
        map(p => p['id']),
        switchMap(id => {
          if (id === 'new') { return of(new Device()); }
          return this.deviceService.findById(id);
        })
      )
      .subscribe({
        next: device => {
          this.device = device;
          this.feedback = {};
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
}
