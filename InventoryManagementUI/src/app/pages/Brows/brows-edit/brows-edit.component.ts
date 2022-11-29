import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BrowsService } from '../brows.service';
import { Brows } from '../brows';
import { map, switchMap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-brows-edit',
  templateUrl: './brows-edit.component.html'
})
export class BrowsEditComponent implements OnInit {

  id!: string;
  brows!: Brows;
  feedback: any = {};

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private browsService: BrowsService) {
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
}
