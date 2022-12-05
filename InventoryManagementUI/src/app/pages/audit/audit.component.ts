import { Component, OnInit } from '@angular/core';
import { ClientService } from '../Client/client.service';

@Component({
  selector: 'ngx-audit',
  templateUrl: './audit.component.html',
  styleUrls: ['./audit.component.scss']
})
export class AuditComponent implements OnInit {
  auditList = [];
  p: number = 1;
  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.load();
  }


  load(): void {
    this.clientService.auditData().subscribe({
      next: (data) => {
      this.auditList = data;
      },
      error: err => {
      }
    });
  }
}
