import { Component, OnInit } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { DashboardService } from '../dashboard/dashboard.service';

@Component({
  selector: 'ngx-treeview',
  templateUrl: './treeview.component.html',
  styleUrls: ['./treeview.component.scss'],
})
export class TreeviewComponent implements OnInit {
  treeoptionsclient: any = {};

  treeoptionsserver: any = {};
  constructor(private theme: NbThemeService, private service: DashboardService) { }

  ngOnInit(): void {
  }
  loadReportData() {
    this.service.getReport().subscribe({
      next: (reportData) => {
        this.treeChartLoadClient(reportData['client-data']);
        this.treeChartLoadserver(reportData['server-data']);
      },
      error: (err) => {},
    });
  }
  treeChartLoadClient(reportData) {


      this.treeoptionsclient = {
        tooltip: {
          trigger: 'item',
          triggerOn: 'mousemove',
        },
        legend: {
          top: '2%',
          left: '3%',
          orient: 'vertical',
          borderColor: '#c23531',
        },
        series: [
          {
            type: 'tree',

            name: 'Client Tree view ',

            data: [reportData],

            top: '5%',
            left: '7%',
            bottom: '2%',
            right: '60%',

            symbolSize: 7,

            label: {
              position: 'left',
              verticalAlign: 'middle',
              align: 'right',
            },

            leaves: {
              label: {
                position: 'right',
                verticalAlign: 'middle',
                align: 'left',
              },
            },

            emphasis: {
              focus: 'descendant',
            },

            expandAndCollapse: true,

            animationDuration: 550,
            animationDurationUpdate: 750,
          },
        ],
      };

  }
  treeChartLoadserver(reportData) {


      this.treeoptionsserver = {
        tooltip: {
          trigger: 'item',
          triggerOn: 'mousemove',
        },
        legend: {
          top: '2%',
          left: '3%',
          orient: 'vertical',
          borderColor: '#c23531',
        },
        series: [
          {
            type: 'tree',

            name: 'Server Tree view',

            data: [reportData],

            top: '5%',
            left: '7%',
            bottom: '2%',
            right: '60%',

            symbolSize: 7,

            label: {
              position: 'left',
              verticalAlign: 'middle',
              align: 'right',
            },

            leaves: {
              label: {
                position: 'right',
                verticalAlign: 'middle',
                align: 'left',
              },
            },

            emphasis: {
              focus: 'descendant',
            },

            expandAndCollapse: true,

            animationDuration: 550,
            animationDurationUpdate: 750,
          },
        ],
      };

  }
  ngAfterViewInit(): void {
    this.loadReportData();
  }
}
