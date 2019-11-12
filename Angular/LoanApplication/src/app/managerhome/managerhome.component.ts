import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { first } from 'rxjs/operators';
import { User } from '../models/user';
import { UserService } from '../_services/user.service';
import { AuthenticationService } from '../_services/authentication.service';
import { ApplicationService } from '@/_services/application.service';
import { Application } from '@/models/application';
import { Router } from '@angular/router';

enum LoanStatus {
New = 1,
Documents_Pending = 2,
Approval_Pending = 3,
Rejected = 4,
Approved = 5,
Disbursed = 6,
Defaulter = 7,
Closed = 8
}


@Component({
  selector: 'app-managerhome',
  templateUrl: './managerhome.component.html',
  styleUrls: ['./managerhome.component.css']
})
export class ManagerhomeComponent implements OnInit {

    public currentUser: User;
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
      var id = this.authenticationService.currentUserValue.id;
      this.applicationService.getAllForAdmin().subscribe( 
                data => {
                    this.applications = data;
                },
                error => {
                    
                }
          );
    }


    isApprovalPending(application: Application)
    {
      let e1 = application.status;
      if( e1  === LoanStatus[LoanStatus.Approval_Pending] )
      {
        return true;
      }
      return false;
    }

    displayDetailsButton(application: Application)
    {
      let c1 = this.isApprovalPending(application);
      if(c1)
      {
        return false;
      }
      return true;
    }

    approve(application: Application)
    {
      application.status=LoanStatus.Approved.toString();
      this.applicationService.update(application).subscribe( 
                data => {
                    application
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
