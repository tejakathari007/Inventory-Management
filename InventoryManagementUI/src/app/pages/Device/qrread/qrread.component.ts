import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NbDialogRef } from '@nebular/theme';
import { DeviceService } from '../device.service';

@Component({
  selector: 'ngx-qrread',
  templateUrl: './qrread.component.html',
  styleUrls: ['./qrread.component.scss']
})
export class QrreadComponent implements OnInit {

  ngOnInit(): void {
  }
  urlLink = '';
  uniqueId = '';
  getTheDeviceInfoInterval: any;
  constructor(protected ref: NbDialogRef<QrreadComponent>,
    private deviceService: DeviceService,
    private router: Router) {
      this.loadTheQr();
    }

  dismiss() {
    this.ref.close();
  }
  loadTheQr() {
    this.deviceService.getQr().subscribe({
      next: data => {
        this.urlLink = data.url;
        this.uniqueId = data.uniqueId;
        this.startCheckingUntilGetResponse();
      },
      error: err => {
      }
    });
  }
  startCheckingUntilGetResponse(){
    this.getTheDeviceInfoInterval = setInterval(() => {
        this.getTheDeviceInfo();
    }, 2000);
    setInterval(()=>{
      clearInterval(this.getTheDeviceInfoInterval);
    },60000);
  }

  getTheDeviceInfo() {
    this.deviceService.getTheDeviceInfo(this.uniqueId).subscribe({
      next: data => {
        if (Object.keys(data).length !== 0) {
          clearInterval(this.getTheDeviceInfoInterval);
          localStorage.setItem('scanDat', JSON.stringify(data));
          this.router.navigate(['/pages/device/devices/new']);
          this.dismiss();
        }
      },
      error: err => {
      }
    });
  }
  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
    //Add 'implements OnDestroy' to the class.
    if(this.getTheDeviceInfoInterval){
      clearInterval(this.getTheDeviceInfoInterval);
    }  }
}
