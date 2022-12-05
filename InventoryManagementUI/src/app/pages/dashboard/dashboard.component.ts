import {AfterViewInit, Component, OnDestroy} from '@angular/core';
import { NbThemeService } from '@nebular/theme';

interface CardSettings {
  title: string;
  iconClass: string;
  type: string;
  value : any
}

@Component({
  selector: 'ngx-dashboard',
  styleUrls: ['./dashboard.component.scss'],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements AfterViewInit, OnDestroy {

  constructor(private theme: NbThemeService) {
  }

  ngAfterViewInit() {
  }

  ngOnDestroy(): void {
  }
}
