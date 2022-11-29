import { Component, OnInit } from '@angular/core';
import { DeviceFilter } from '../device-filter';
import { DeviceService } from '../device.service';
import { Device } from '../device';

@Component({
  selector: 'app-device',
  templateUrl: 'device-list.component.html'
})
export class DeviceListComponent implements OnInit {
  viewType = 'listview';
  filter = new DeviceFilter();
  selectedDevice!: Device;
  feedback: any = {};

  get deviceList(): Device[] {
    return this.deviceService.deviceList;
  }

  constructor(private deviceService: DeviceService) {
  }

  ngOnInit() {
    this.search();
  }
  checkAccess() {
    const userData = JSON.parse(localStorage.getItem('userDetails'));
    if(userData && userData.role=="ROLE_ADMIN"){
      return true;
    }
    else {
      return false;
    }
  }

  search(): void {
    this.deviceService.load(this.filter);
  }

  select(selected: Device): void {
    this.selectedDevice = selected;
  }

  delete(device: Device): void {
    if (confirm('Are you sure?')) {
      this.deviceService.delete(device).subscribe({
        next: () => {
          this.feedback = {type: 'success', message: 'Delete was successful!'};
          setTimeout(() => {
            this.search();
          }, 1000);
        },
        error: err => {
          this.feedback = {type: 'warning', message: 'Error deleting.'};
        }
      });
    }
  }
}
