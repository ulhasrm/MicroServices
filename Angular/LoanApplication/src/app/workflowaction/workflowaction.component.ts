import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '@/_services/alert.service';
import { FormBuilder } from '@angular/forms';
import { AuthenticationService } from '@/_services/authentication.service';
import { ApplicationService } from '@/_services/application.service';
import { WorkflowTransition } from '@/models/workflowaction';
import { WorkFlowActionResult } from '@/models/WorkflowActionResult';
import { WorkflowviewComponent } from '@/workflowview/workflowview.component';
import { MatDialog } from '@angular/material';
import * as mermaidAPI from '../../assets/mermaidAPI.js';

@Component({
  selector: 'app-workflowaction',
  templateUrl: './workflowaction.component.html',
  styleUrls: ['./workflowaction.component.css']
})
export class WorkflowactionComponent implements OnInit {
  @Input() applicationId: number;
  workflowTransition: WorkflowTransition[];
  actionResult: WorkFlowActionResult;
  loading = false;

  constructor(private route: ActivatedRoute,
    private applicationService: ApplicationService,
    public authenticationService: AuthenticationService,
    private formBuilder: FormBuilder,
    private alertService: AlertService,
    private router: Router,
    public dialog: MatDialog) { }

  ngOnInit() {
    this.applicationService.getNextActionsForAplication(this.applicationId, this.authenticationService.currentUserValue.id).subscribe(
      data => {
        this.workflowTransition = data;
      },
      error => {

      }
    );
  }

  performAction(transitionId: number) {
    this.loading = true;

    this.applicationService.performAction(this.applicationId, transitionId).subscribe(
      data => {
        if (data.success) {
          this.alertService.success(data.message);
          this.router.navigate(['/']);
        }
        else {
          this.alertService.error(data.message);
          this.loading = false;
        }
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
      }
    );
  }

  openPertChartDialog(transitionId: number) {
    this.dialog.open(WorkflowviewComponent, {
      data: {
        applicationId: this.applicationId,
        transitionId: transitionId,
        top: 10,
        parent: this,
        height: "98%"
      }
    });
  }

}
