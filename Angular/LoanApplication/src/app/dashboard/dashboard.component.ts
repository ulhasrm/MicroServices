import { Component, OnInit } from '@angular/core';
import { UserService } from '@/_services/user.service';
import { AuthenticationService } from '@/_services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  isAdmin: boolean;

  constructor(public authenticationService: AuthenticationService,
    private userService: UserService,
    private router: Router) {
    this.isAdmin = authenticationService.currentUserValue.admin;
  }

  ngOnInit() {
    /*if (this.isAdmin) {
      this.router.navigate(['/managerhome']);
    }
    else {
      this.router.navigate(['/userhome']);
    }*/
  }

}
