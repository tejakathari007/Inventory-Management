import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OthersService } from '../others.service';
import { Others } from '../others';
import { map, switchMap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-others-edit',
  templateUrl: './others-edit.component.html'
})
export class OthersEditComponent implements OnInit {

  id!: string;
  others!: Others;
  feedback: any = {};

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private othersService: OthersService) {
  }

  ngOnInit() {
    this
      .route
      .params
      .pipe(
        map(p => p['id']),
        switchMap(id => {
          if (id === 'new') { return of(new Others()); }
          return this.othersService.findById(id);
        })
      )
      .subscribe({
        next: others => {
          this.others = others;
          this.feedback = {};
        },
        error: err => {
          this.feedback = {type: 'warning', message: 'Error loading'};
        }
      });
  }

  save() {
    this.othersService.save(this.others).subscribe({
      next: others => {
        this.others = others;
        this.feedback = {type: 'success', message: 'Save was successful!'};
        setTimeout(async () => {
          await this.router.navigate(['/pages/others/otherses']);
        }, 1000);
      },
      error: err => {
        this.feedback = {type: 'warning', message: 'Error saving'};
      }
    });
  }

  async cancel() {
    await this.router.navigate(['/pages/others/otherses']);
  }
}
