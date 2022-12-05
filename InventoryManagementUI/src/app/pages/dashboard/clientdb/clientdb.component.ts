import { AfterViewInit, Component, OnInit } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { loadavg } from 'os';
import { DashboardService } from '../dashboard.service';

@Component({
  selector: 'ngx-clientdb',
  templateUrl: './clientdb.component.html',
  styleUrls: ['./clientdb.component.scss']
})
export class ClientdbComponent implements AfterViewInit, OnInit {
  pieoptionsState: any = {};
  baroptionsmaker: any = {};
  piethemeSubscriptionState: any;
  barthemeSubscriptionmaker: any;

  statusCards = [];
  constructor(private theme: NbThemeService,
    private service: DashboardService) {
    this.statusCards = [ {
      iconClass:'fa fa-regular fa-server',
      type:'success',
      title: 'Servers',
      value: 0
    }];
   }

  ngOnInit(): void {
  }
  ngAfterViewInit(){
    this.loadTheCount();
    this.pieChartLoadstate();
    this.barChartLoadmaker();
  }
  loadTheCount(){
    this.service.DeviceCountPer().subscribe({
      next: (stateData) => {
        this.statusCards[0].value = stateData.DeviceCount;
      },
      error: (err) => {},
    });
  }
  pieChartLoadstate(){
    this.service.clientState().subscribe({
      next: (stateData) => {
        let lables  = [];
        let values = [];
        stateData.data.forEach(element => {
          lables.push(element.name);
          values.push(element.value);
        });
    this.piethemeSubscriptionState = this.theme.getJsTheme().subscribe(config => {

      const colors = config.variables;
      const echarts: any = config.variables.echarts;

      this.pieoptionsState = {
        backgroundColor: echarts.bg,
        color: [colors.primaryLight],
        title: {
          text: 'BrowsersCount Vs Clients',
          subtext: '',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow',
          },
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true,
        },
        xAxis: [
          {
            type: 'category',
            data: lables,
            axisTick: {
              alignWithLabel: true,
            },
            axisLine: {
              lineStyle: {
                color: echarts.axisLineColor,
              },
            },
            axisLabel: {
              textStyle: {
                color: echarts.textColor,
              },
            },
          },
        ],
        yAxis: [
          {
            type: 'value',
            axisLine: {
              lineStyle: {
                color: echarts.axisLineColor,
              },
            },
            splitLine: {
              lineStyle: {
                color: echarts.splitLineColor,
              },
            },
            axisLabel: {
              textStyle: {
                color: echarts.textColor,
              },
            },
          },
        ],
        series: [
          {
            name: 'Score',
            type: 'bar',
            barWidth: '60%',
            data: values,
          },
        ],
      };
    });
  },
  error: (err) => {},
});
  }

  
  barChartLoadmaker(){
    this.service.clientmaker().subscribe({
      next: (stateData) => {
        let lables  = [];
        let values = [];
        stateData.data.forEach(element => {
          lables.push(element.name);
          values.push(element.value);
        });
    this.barthemeSubscriptionmaker = this.theme.getJsTheme().subscribe(config => {

      const colors: any = config.variables;
      const echarts: any = config.variables.echarts;

      this.baroptionsmaker = {
        backgroundColor: echarts.bg,
        color: [colors.primaryLight],
        title: {
          text: 'DeviceCount Vs Clients',
          subtext: '',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow',
          },
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true,
        },
        yAxis: [
          {
            type: 'category',
            data: lables,
            axisTick: {
              alignWithLabel: true,
            },
            axisLine: {
              lineStyle: {
                color: echarts.axisLineColor,
              },
            },
            axisLabel: {
              textStyle: {
                color: echarts.textColor,
              },
            },
          },
        ],
        xAxis: [
          {
            type: 'value',
            axisLine: {
              lineStyle: {
                color: echarts.axisLineColor,
              },
            },
            splitLine: {
              lineStyle: {
                color: echarts.splitLineColor,
              },
            },
            axisLabel: {
              textStyle: {
                color: echarts.textColor,
              },
            },
          },
        ],
        series: [
          {
            name: 'Score',
            type: 'bar',
            barWidth: '60%',
            data: values,
          },
        ],
      };
    });
  },
  error: (err) => {},
});
  }
}
