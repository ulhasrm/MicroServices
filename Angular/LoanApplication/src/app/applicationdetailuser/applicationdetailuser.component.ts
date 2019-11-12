import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';
import { ApplicationService } from '@/_services/application.service';
import { Application } from '@/models/application';
import { User } from '@/models/user';
import { Subscription } from 'rxjs';
import { AuthenticationService } from '@/_services/authentication.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AlertService } from '@/_services/alert.service';

@Component({
  selector: 'app-applicationdetailuser',
  templateUrl: './applicationdetailuser.component.html',
  styleUrls: ['./applicationdetailuser.component.css']
})
export class ApplicationdetailuserComponent implements OnInit {

  applicationId;
  application: Application;
  public currentUser: User;
  currentUserSubscription: Subscription;
  applicationDetailForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(private route: ActivatedRoute,
              private applicationService: ApplicationService,
              public authenticationService: AuthenticationService,
              private formBuilder: FormBuilder,
              private alertService: AlertService,
              private router: Router) {
    this.route.params.subscribe(params => this.applicationId = params['id']);
    this.currentUser = this.authenticationService.currentUserValue;
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      this.currentUser = user;
    });
  }

  ngOnInit() {
    this.applicationDetailForm = this.formBuilder.group({
      applicationAction: ['', Validators.required]
    });

    this.applicationService.getById(this.applicationId).subscribe(
      data => {
        this.application = data;
      },
      error => {

      }
    );
  }

  public get f() { return this.applicationDetailForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.applicationDetailForm.invalid) {
      return;
    }

    this.loading = true;
    this.application.status = this.f.applicationAction.value;
    /*this.applicationService.update(this.application)
      .subscribe(
        data => {
          this.alertService.success('Loan Application Updated successfully', true);
          this.router.navigate(['/']);
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });*/

  }

}
