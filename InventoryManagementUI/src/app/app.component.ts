/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AnalyticsService } from './@core/utils/analytics.service';
import { SeoService } from './@core/utils/seo.service';

@Component({
  selector: 'ngx-app',
  template: '<router-outlet></router-outlet>',
})
export class AppComponent implements OnInit {

  constructor(private analytics: AnalyticsService, private seoService: SeoService,
    private router: Router) {
  }

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if(!token){
      this.router.navigate(['/auth']);
    } 
    this.analytics.trackPageViews();
    this.seoService.trackCanonicalChanges();
  }
}
