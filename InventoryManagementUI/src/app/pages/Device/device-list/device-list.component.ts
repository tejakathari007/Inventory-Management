import { Component, OnInit } from '@angular/core';
import { DeviceFilter } from '../device-filter';
import { DeviceService } from '../device.service';
import { Device } from '../device';
import { QrreadComponent } from '../qrread/qrread.component';
import { NbDialogService } from '@nebular/theme';

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

  constructor(private deviceService: DeviceService,
    private dialogService: NbDialogService) {
  }

  ngOnInit() {
    this.load();
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
  load(): void {
    this.deviceService.load(this.filter);
  }
  search(): void {
    this.deviceService.searchfilter(this.filter);
  }

  select(selected: Device): void {
    this.selectedDevice = selected;
  }
  getQr(){

    this.dialogService.open(QrreadComponent);
    
  }
  delete(device: Device): void {
    if (confirm('Are you sure?')) {
      this.deviceService.delete(device).subscribe({
        next: () => {
          this.feedback = {type: 'success', message: 'Delete was successful!'};
          setTimeout(() => {
            this.load();
          }, 1000);
        },
        error: err => {
          this.feedback = {type: 'warning', message: 'Error deleting.'};
        }
      });
    }
  }
}
