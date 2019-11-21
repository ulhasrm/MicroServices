import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { first } from 'rxjs/operators';

import { User } from '../models/user';
import { UserService } from '../_services/user.service';
import { AuthenticationService } from '../_services/authentication.service';
import { ApplicationService } from '@/_services/application.service';
import { Application } from '@/models/application';
import { Router } from '@angular/router';

@Component({
    selector: 'app-userhome',
    templateUrl: './userhome.component.html',
    styleUrls: ['./userhome.component.css']
})
export class UserhomeComponent implements OnInit {

    public currentUser: User;
    currentUserSubscription: Subscription;
    users: User[] = [];
    applications: Application[] = [];

    constructor(
        public authenticationService: AuthenticationService,
        private userService: UserService,
        private applicationService: ApplicationService,
        private router: Router ){
        this.currentUser = this.authenticationService.loggedUser;
        this.currentUser = this.authenticationService.currentUserValue;
        this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
            this.currentUser = user;
        });
    }

    ngOnInit() {
      var id = this.authenticationService.currentUserValue.id;
      this.applicationService.getAll(id).subscribe( 
                data => {
                    this.applications = data;
                },
                error => {
                    //this.loading = false;
                }
          );
    }

    detail(application: Application)
    {
      this.router.navigate(['applicationDetailUser', application.id]);
    }

    

}
