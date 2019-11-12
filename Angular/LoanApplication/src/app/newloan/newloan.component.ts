import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AlertService } from '../_services/alert.service';
import { UserService, } from '../_services/user.service';
import { AuthenticationService } from '../_services/authentication.service';
import { ApplicationService } from '@/_services/application.service';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-newloan',
  templateUrl: './newloan.component.html',
  styleUrls: ['./newloan.component.css']
})
export class NewloanComponent implements OnInit {
  loanForm: FormGroup;
  loading = false;
  submitted = false;
  loanAppUrl = environment.loanappUrl;

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private applicationService: ApplicationService,
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private alertService: AlertService) {
    if (!this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.loanForm = this.formBuilder.group({
      loanType: ['', Validators.required],
      amountRequired: ['', Validators.required]
    });

  }
  // convenience getter for easy access to form fields
  public get f() { return this.loanForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loanForm.invalid) {
      return;
    }

    this.loading = true;
    this.applicationService.create(this.f.loanType.value, this.f.amountRequired.value, this.authenticationService.currentUserValue.id )
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Loan Application successful', true);
          this.router.navigate(['/userhome']);
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
      
  }

}
