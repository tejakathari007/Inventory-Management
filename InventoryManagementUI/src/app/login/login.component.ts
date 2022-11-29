import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { QyauthService } from '../qyauth/qyauth.service';

@Component({
  selector: 'ngx-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private _QyauthService:QyauthService,
    private router: Router) { }
  errors =[];
  submitted = false;
  user = {
    userName:'',
    password:''
  }
  ngOnInit(): void {
  }

  login(){
    this._QyauthService.login(this.user).subscribe({
      next: logindata => {
        this.errors = ['login successfull'];
        localStorage.setItem('token',logindata.accessToken);
        localStorage.setItem('userDetails', JSON.stringify(logindata));
        setTimeout(async () => {
          await this.router.navigate(['/pages']);
        }, 1000);
      },
      error: err => {
        this.errors = ['please check username and password'];
      }
    });
  }
}
