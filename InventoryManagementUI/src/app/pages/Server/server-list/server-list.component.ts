import { Component, OnInit } from '@angular/core';
import { ServerFilter } from '../server-filter';
import { ServerService } from '../server.service';
import { Server } from '../server';
import { Router } from '@angular/router';

@Component({
  selector: 'app-server',
  templateUrl: 'server-list.component.html'
})
export class ServerListComponent implements OnInit {
  viewType = 'listview';
  filter = new ServerFilter();
  selectedServer!: Server;
  feedback: any = {};

  get serverList(): Server[] {
    return this.serverService.serverList;
  }

  constructor(private serverService: ServerService,
    private router: Router) {
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
  ngOnInit() {
    this.load();
  }

  load(): void {
    this.serverService.load(this.filter);
  }
  search(): void {
    this.serverService.searchfilter(this.filter);
  }

  select(selected: Server): void {
    this.selectedServer = selected;
  }

  delete(server: Server): void {
    if (confirm('Are you sure?')) {
      this.serverService.delete(server).subscribe({
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

 async deplucated(server: Server) {
    await this.router.navigate(['/pages/server/servers',server.id,'duplicate']);
  }
}
