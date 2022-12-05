import { AfterViewInit, Component, OnInit } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { DashboardService } from '../dashboard.service';

@Component({
  selector: 'ngx-devicedb',
  templateUrl: './devicedb.component.html',
  styleUrls: ['./devicedb.component.scss']
})
export class DevicedbComponent implements AfterViewInit, OnInit {

  pieoptionsState: any = {};
  baroptionsmaker: any = {};
  baroptionsos: any = {};
  piethemeSubscriptionState: any;
  barthemeSubscriptionmaker: any;
  barthemeSubscriptionos: any;

  statusCards = [];
  constructor(private theme: NbThemeService,
    private service: DashboardService) {
    this.statusCards = [ {
      iconClass:'fa fa-regular fa-mobile',
      type:'success',
      title: 'Devices',
      value: 0
    }];
   }

  ngOnInit(): void {
   
  }
  ngAfterViewInit(){
    this.loadTheCount();
    this.pieChartLoadstate();
    this.barChartLoadmaker();
    this.barChartLoados();
  }
  pieChartLoadstate(){

    this.service.deviceState().subscribe({
      next: (stateData) => {
    this.piethemeSubscriptionState = this.theme.getJsTheme().subscribe(config => {

      const colors = config.variables;
      const echarts: any = config.variables.echarts;

      this.pieoptionsState = {
        backgroundColor: echarts.bg,
        // color: [colors.warningLight, colors.infoLight, colors.dangerLight, colors.successLight, colors.primaryLight],
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)',
        },
        title: {
          text: 'DeviceCount By State',
          subtext: '',
          left: 'center'
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
    this.service.devicemaker().subscribe({
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
          text: 'DeviceCount By Maker',
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
  barChartLoados(){
    this.service.deviceos().subscribe({
      next: (stateData) => {
        let lables  = [];
        let values = [];
        stateData.data.forEach(element => {
          lables.push(element.name);
          values.push(element.value);
        });
    this.barthemeSubscriptionos = this.theme.getJsTheme().subscribe(config => {

      const colors: any = config.variables;
      const echarts: any = config.variables.echarts;

      this.baroptionsos = {
        backgroundColor: echarts.bg,
        color: [colors.primaryLight],
        title: {
          text: 'DeviceCount By OS',
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
  loadTheCount(){
    this.service.DeviceCountPer().subscribe({
      next: (stateData) => {
        this.statusCards[0].value = stateData.DeviceCount;
      },
      error: (err) => {},
    });
  }
  ngOnDestroy(): void {
    if(this.piethemeSubscriptionState) {
      this.piethemeSubscriptionState.unsubscribe();
    }

    if(this.barthemeSubscriptionmaker){
      this.barthemeSubscriptionmaker.unsubscribe();
    }

    if(this.barthemeSubscriptionos) {
      this.barthemeSubscriptionos.unsubscribe();
    }
     }
}
