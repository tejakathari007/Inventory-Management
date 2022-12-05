import { Component, OnInit } from '@angular/core';
import { ClientFilter } from '../client-filter';
import { ClientService } from '../client.service';
import { Client } from '../client';

@Component({
  selector: 'app-client',
  templateUrl: 'client-list.component.html'
})
export class ClientListComponent implements OnInit {
  viewType = 'listview';
  filter = new ClientFilter();
  selectedClient!: Client;
  feedback: any = {};
  get clientList(): Client[] {
    return this.clientService.clientList;
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
  constructor(private clientService: ClientService) {
  }
  ngOnInit() {
    this.load();
  }
  sendMail(item:Client){
    if(item.email) {
      this.feedback = {type: 'success', message: 'Mail Processing......'};
      this.clientService.sendMail(item.id).subscribe({
        next: () => {
          this.feedback = {type: 'success', message: 'Mail has been Sent!'};
          setTimeout(() => {
            this.feedback = {};
          }, 4000);
        },
        error: err => {
          this.feedback = {type: 'success', message: 'Mail has been Sent!'};
          setTimeout(() => {
            this.feedback = {};
          }, 4000);
        }
      });
    } else {
      alert("Mail id is missing in client");
    }
  }
  load(): void {
    this.clientService.load(this.filter);
  }
  search(): void {
    this.clientService.searchfilter(this.filter);
  }

  select(selected: Client): void {
    this.selectedClient = selected;
  }

  delete(client: Client): void {
    if (confirm('Are you sure?')) {
      this.clientService.delete(client).subscribe({
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
