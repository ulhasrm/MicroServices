import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { first } from 'rxjs/operators';
import { User } from '../models/user';
import { UserService } from '../_services/user.service';
import { AuthenticationService } from '../_services/authentication.service';
import { ApplicationService } from '@/_services/application.service';
import { Application } from '@/models/application';
import { Router } from '@angular/router';
import { LoggedInUser } from '@/models/logerInUser';

@Component({
  selector: 'app-managerhome',
  templateUrl: './managerhome.component.html',
  styleUrls: ['./managerhome.component.css']
})
export class ManagerhomeComponent implements OnInit {

    public currentUser: LoggedInUser;
    currentUserSubscription: Subscription;
    users: User[] = [];
    applications: Application[] = [];

    constructor(
        public authenticationService: AuthenticationService,
        private userService: UserService,
        private applicationService: ApplicationService,
        private router: Router){
        this.currentUser = this.authenticationService.loggedUser;
        this.currentUser = this.authenticationService.currentUserValue;
        this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
            this.currentUser = user;
        });
    }

    ngOnInit() {
      this.applicationService.getAllForAdmin().subscribe( 
                data => {
                    this.applications = data;
                },
                error => {
                    
                }
          );
    }

    detail(application: Application)
    {
      this.router.navigate(['applicationDetail', application.id]);
    }

}
