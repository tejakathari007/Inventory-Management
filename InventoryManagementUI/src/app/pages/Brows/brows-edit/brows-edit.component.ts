import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BrowsService } from '../brows.service';
import { Brows } from '../brows';
import { map, switchMap } from 'rxjs/operators';
import { of } from 'rxjs';
import { ServerService } from '../../Server/server.service';
import { ClientService } from '../../Client/client.service';

@Component({
  selector: 'app-brows-edit',
  templateUrl: './brows-edit.component.html'
})
export class BrowsEditComponent implements OnInit, AfterViewInit {

  id!: string;
  brows!: Brows;
  feedback: any = {};
  severList = [];
  clientList = [];
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private browsService: BrowsService,
    private clientService: ClientService,
    private serverService: ServerService) {
  }

  ngOnInit() {
    this
      .route
      .params
      .pipe(
        map(p => p['id']),
        switchMap(id => {
          if (id === 'new') { return of(new Brows()); }
          return this.browsService.findById(id);
        })
      )
      .subscribe({
        next: brows => {
          this.brows = brows;
          this.feedback = {};
        },
        error: err => {
          this.feedback = {type: 'warning', message: 'Error loading'};
        }
      });
  }

  save() {
    this.browsService.save(this.brows).subscribe({
      next: brows => {
        this.brows = brows;
        this.feedback = {type: 'success', message: 'Save was successful!'};
        setTimeout(async () => {
          await this.router.navigate(['/pages/browser/browses']);
        }, 1000);
      },
      error: err => {
        this.feedback = {type: 'warning', message: 'Error saving'};
      }
    });
  }

  async cancel() {
    await this.router.navigate(['/pages/browser/browses']);
  }
  ngAfterViewInit(): void {
    //Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
    //Add 'implements AfterViewInit' to the class.
    this.load();
  }
  getseverList(){
    return this.serverService.serverList;
  }

  getclientList(){
    return this.clientService.clientList;
  }
  load(): void {
    const filter:any = {};
    this.serverService.load(filter);
    this.clientService.load(filter);
  }
  compare(val1, val2) {
    if(val1 && val2)
      return val1.id === val2.id;
    return false;
  }
  compareserver(val1, val2) {
    if(val1 && val2)
    return val1.id === val2.id;
  return false;
  }
}
