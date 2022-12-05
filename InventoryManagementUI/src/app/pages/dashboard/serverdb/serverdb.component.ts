import { AfterViewInit, Component, OnInit } from "@angular/core";
import { NbThemeService } from "@nebular/theme";
import { DashboardService } from "../dashboard.service";

@Component({
  selector: "ngx-serverdb",
  templateUrl: "./serverdb.component.html",
  styleUrls: ["./serverdb.component.scss"],
})
export class ServerdbComponent implements AfterViewInit, OnInit {
  pieoptionsState: any = {};
  baroptionsregion: any = {};
  baroptionshosting: any = {};
  baroptionsos: any = {};
  piethemeSubscriptionState: any;
  barthemeSubscriptionregion: any;
  barthemeSubscriptionhosting: any;
  barthemeSubscriptionos: any;
  statusCards = [];
  constructor(
    private theme: NbThemeService,
    private service: DashboardService
  ) {
    this.statusCards = [
      {
        iconClass: "fa fa-regular fa-server",
        type: "success",
        title: " Servers",
        value: 0,
      },
      {
        iconClass: "fa fa-regular fa-mobile",
        type: "success",
        title: "Avg Devices per server",
        value: 0,
      },
      {
        iconClass: "fa fa-yin-yang",
        type: "success",
        title: "Avg Browsers per server",
        value: 0,
      },
    ];
  }

  ngOnInit(): void {}
  ngAfterViewInit() {
    this.loadTheCount();
    this.pieChartLoadstate();
    this.barChartLoadregion();
    this.barChartLoadhosting();
    this.barChartLoados();
  }

  pieChartLoadstate() {
    this.service.serverState().subscribe({
      next: (stateData) => {
        this.piethemeSubscriptionState = this.theme
          .getJsTheme()
          .subscribe((config) => {
            const colors = config.variables;
            const echarts: any = config.variables.echarts;

            this.pieoptionsState = {
              backgroundColor: echarts.bg,
              title: {
                text: 'ServerCount By State',
                subtext: '',
                left: 'center'
              },
              tooltip: {
                trigger: "item",
                formatter: "{a} <br/>{b} : {c} ({d}%)",
              },
              legend: {
                orient: "vertical",
                left: "left",
                textStyle: {
                  color: echarts.textColor,
                },
              },
              series: [
                {
                  name: "State",
                  type: "pie",
                  radius: "80%",
                  center: ["50%", "50%"],
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
  barChartLoadregion() {
    this.service.serverregion().subscribe({
      next: (stateData) => {
        let lables  = [];
        let values = [];
        stateData.data.forEach(element => {
          lables.push(element.name);
          values.push(element.value);
        });
        this.barthemeSubscriptionregion = this.theme
          .getJsTheme()
          .subscribe((config) => {
            const colors: any = config.variables;
            const echarts: any = config.variables.echarts;

            this.baroptionsregion = {
              backgroundColor: echarts.bg,
              color: [colors.primaryLight],
              title: {
                text: 'ServerCount By Region',
                subtext: '',
                left: 'center'
              },
              tooltip: {
                trigger: "axis",
                axisPointer: {
                  type: "shadow",
                },
              },
              grid: {
                left: "3%",
                right: "4%",
                bottom: "3%",
                containLabel: true,
              },
              yAxis: [
                {
                  type: "category",
                  data: lables,
                  //["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
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
                  type: "value",
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
                  name: "Score",
                  type: "bar",
                  barWidth: "60%",
                  data: values,
                },
              ],
            };
          });
      },
      error: (err) => {},
    });
  }
  barChartLoadhosting() {
    this.service.serverhosting().subscribe({
      next: (stateData) => {
        let lables  = [];
        let values = [];
        stateData.data.forEach(element => {
          lables.push(element.name);
          values.push(element.value);
        });
        this.barthemeSubscriptionhosting = this.theme
          .getJsTheme()
          .subscribe((config) => {
            const colors: any = config.variables;
            const echarts: any = config.variables.echarts;

            this.baroptionshosting = {
              backgroundColor: echarts.bg,
              color: [colors.primaryLight],
              title: {
                text: 'ServerCount By HostingType',
                subtext: '',
                left: 'center'
              },
              tooltip: {
                trigger: "axis",
                axisPointer: {
                  type: "shadow",
                },
              },
              grid: {
                left: "3%",
                right: "4%",
                bottom: "3%",
                containLabel: true,
              },
              xAxis: [
                {
                  type: "category",
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
                  type: "value",
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
                  name: "Score",
                  type: "bar",
                  barWidth: "60%",
                  data: values,
                },
              ],
            };
          });
      },
      error: (err) => {},
    });
  }
  barChartLoados() {
    this.service.serveros().subscribe({
      next: (stateData) => {
        let lables  = [];
        let values = [];
        stateData.data.forEach(element => {
          lables.push(element.name);
          values.push(element.value);
        });
        this.barthemeSubscriptionos = this.theme
          .getJsTheme()
          .subscribe((config) => {
            const colors: any = config.variables;
            const echarts: any = config.variables.echarts;

            this.baroptionsos = {
              backgroundColor: echarts.bg,
              color: [colors.primaryLight],
              title: {
                text: 'ServerCount By OS',
                subtext: '',
                left: 'center'
              },
              tooltip: {
                trigger: "axis",
                axisPointer: {
                  type: "shadow",
                },
              },
              grid: {
                left: "3%",
                right: "4%",
                bottom: "3%",
                containLabel: true,
              },
              yAxis: [
                {
                  type: "category",
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
                  type: "value",
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
                  name: "Score",
                  type: "bar",
                  barWidth: "60%",
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
    this.service.serverDeviceCountPer().subscribe({
      next: (stateData) => {
        this.statusCards[1].value = stateData.AverageDeviceCount;
      },
      error: (err) => {},
    });
    this.service.serverBrowserCountPer().subscribe({
      next: (stateData) => {
        this.statusCards[2].value = stateData.AverageBrowserCount;
      },
      error: (err) => {},
    });
    this.service.serverAllServers().subscribe({
      next: (stateData) => {
        this.statusCards[0].value = stateData.Count;
      },
      error: (err) => {},
    });
  }
  ngOnDestroy(): void {
    if(this.piethemeSubscriptionState) {
      this.piethemeSubscriptionState.unsubscribe();
    }

    if(this.barthemeSubscriptionregion) {
      this.barthemeSubscriptionregion.unsubscribe();
    }

    if(this.barthemeSubscriptionhosting){
      this.barthemeSubscriptionhosting.unsubscribe();
    }

    if(this.barthemeSubscriptionos) {
      this.barthemeSubscriptionos.unsubscribe();
    }
  }
}
