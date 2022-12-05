import { AfterViewInit, Component, OnInit } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { DashboardService } from '../dashboard.service';

@Component({
  selector: 'ngx-browserdb',
  templateUrl: './browserdb.component.html',
  styleUrls: ['./browserdb.component.scss']
})
export class BrowserdbComponent implements AfterViewInit, OnInit {
  pieoptionsState: any = {};
  baroptionsmaker: any = {};
  baroptionsothers: any = {};
  piethemeSubscriptionState: any;
  barthemeSubscriptionmaker: any;
  barthemeSubscriptionothers: any;
  statusCards = [];
  constructor(private theme: NbThemeService,
    private service: DashboardService) {
    this.statusCards = [{
      iconClass:'fa fa-yin-yang',
      type:'success',
      title: 'Browsers',
      value: 0
    }]
   }

  ngOnInit(): void {
  }
  ngAfterViewInit(){
    this.loadTheCount();
    this.pieChartLoadstate();
    this.barChartLoadmaker();
    this.barChartLoadothers();
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
    this.service.browserState().subscribe({
      next: (stateData) => {
    this.piethemeSubscriptionState = this.theme.getJsTheme().subscribe(config => {

      const colors = config.variables;
      const echarts: any = config.variables.echarts;

      this.pieoptionsState = {
        backgroundColor: echarts.bg,
        title: {
          text: 'BrowserCount By State',
          subtext: '',
          left: 'center'
        },
        // color: [colors.warningLight, colors.infoLight, colors.dangerLight, colors.successLight, colors.primaryLight],
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)',
        },
        legend: {
          orient: 'vertical',
          left: 'left',
         // data: ['USA', 'Germany', 'France', 'Canada', 'Russia'],
          textStyle: {
            color: echarts.textColor,
          },
        },
        series: [
          {
            name: 'State',
            type: 'pie',
            radius: '80%',
            center: ['50%', '50%'],
            data: stateData.data,
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: echarts.itemHoverShadowColor,
              },
            },
            label: {
              normal: {
                textStyle: {
                  color: echarts.textColor,
                },
              },
            },
            labelLine: {
              normal: {
                lineStyle: {
                  color: echarts.axisLineColor,
                },
              },
            },
          },
        ],
      };
    });
  },
  error: (err) => {},
});
  }

  barChartLoadmaker(){
    this.service.browsermaker().subscribe({
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
          text: 'BrowserCount By Name',
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
            data:lables,
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
  barChartLoadothers(){
    this.service.browserothers().subscribe({
      next: (stateData) => {
        let lables  = [];
        let values = [];
        stateData.data.forEach(element => {
          lables.push(element.name);
          values.push(element.value);
        });
    this.barthemeSubscriptionothers = this.theme.getJsTheme().subscribe(config => {

      const colors: any = config.variables;
      const echarts: any = config.variables.echarts;

      this.baroptionsothers = {
        backgroundColor: echarts.bg,
        color: [colors.primaryLight],
        title: {
          text: 'OtherDevices Count',
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
            data:lables,
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
