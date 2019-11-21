import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { first } from 'rxjs/operators';

import { User } from '../models/user';
import { UserService } from '../_services/user.service';
import { AuthenticationService } from '../_services/authentication.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    public currentUser: User;
    currentUserSubscription: Subscription;
    users: User[] = [];

    constructor(
        public authenticationService: AuthenticationService,
        private userService: UserService,
        private router: Router) {
        this.currentUser = this.authenticationService.loggedUser;
        this.currentUser = this.authenticationService.currentUserValue;
        this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
            this.currentUser = user;
        });
    }

    ngOnInit() {
        //this.router.navigate(['/dashboard']);
        /*if (this.authenticationService.isAdmin == true) {
            this.router.navigate(['/managerhome']);
        }
        else {
            this.router.navigate(['/userhome']);
        }*/
    }

    detail(role: String)
    {
      this.router.navigate(['login', role]);
    }


}
