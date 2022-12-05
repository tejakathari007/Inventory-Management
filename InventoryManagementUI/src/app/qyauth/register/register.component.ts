import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { QyauthService } from '../qyauth.service';

@Component({
  selector: 'ngx-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

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

  register(){
    this.errors =[];
    this.user['role'] = 'ROLE_EDITOR';
    this._QyauthService.register(this.user).subscribe({
      next: data => {
        if(!data.accessToken){
          this.errors = [data.message];
        } else {
          this.errors = ['login successfull'];
          localStorage.setItem('token',data.accessToken);
          localStorage.setItem('userDetails', JSON.stringify(data));
          setTimeout(async () => {
            await this.router.navigate(['/pages']);
          }, 100);
        } 
      },
      error: err => {
        if(err && err.error && err.error.text){
          this.errors = [err.error.text];
        } else {
          this.errors = ['Error in user creation'];
        }
      }
    });
  }
}
