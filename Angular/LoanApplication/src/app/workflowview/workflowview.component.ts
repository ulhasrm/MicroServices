import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import * as $ from 'jquery';
import { Observable, forkJoin, ReplaySubject } from 'rxjs';
import { DOCUMENT } from '@angular/common';
import { ApplicationService } from '@/_services/application.service';
import { AlertService } from '@/_services/alert.service';
declare var Quill: any;
declare var mermaidAPI: any;


@Component({
  selector: 'app-workflowview',
  templateUrl: './workflowview.component.html',
  styleUrls: ['./workflowview.component.css']
})
export class WorkflowviewComponent implements OnInit {
  applicationID: number;
  transitionId: number;
  private _loadedLibraries: { [url: string]: ReplaySubject<any> } = {};

  constructor(public dialogRef: MatDialogRef<WorkflowviewComponent>,
    @Inject(MAT_DIALOG_DATA) public injected_data: any,
    @Inject(DOCUMENT) private readonly document: any,
    private applicationService: ApplicationService,
    private alertService: AlertService) {
    this.applicationID = injected_data.applicationId;
    this.transitionId = injected_data.transitionId;
  }

  ngOnInit() {
    this.callLazyLoadMermaid();
    //this.drawDummyChart();
  }

  drawDummyChart() {
    var result = "graph TD; " +
      "A-->B;" +
      "A-->C;" +
      "B-->D;" +
      "C-->D;";

    //var result1 = "\ngraph TB\n52(\"<span style='color:#8bc34a'>New Application</span>\")\n53(\"<span style='color:#000000'>Application Submitted</span>\")\n54(\"<span style='color:#000000'>Document Reviewing</span>\")\n55(\"<span style='color:#000000'>Documents Approved</span>\")\n102(\"<span style='color:#000000'>Documents Rejected</span>\")\n56(\"<span style='color:#000000'>Credit Limit Checking</span>\")\n62(\"<span style='color:#000000'>Loan Rejected</span>\")\n57(\"<span style='color:#000000'>Credit Approved</span>\")\n58(\"<span style='color:#000000'>Personal Verification - InProgress</span>\")\n59(\"<span style='color:#000000'>Personal Verification Success</span>\")\n60(\"<span style='color:#000000'>Personal Verification Failed</span>\")\n61(\"<span style='color:#000000'>Loan Approved</span>\")\n63(\"<span style='color:#000000'>Loan Documentation - InProcess</span>\")\n64(\"<span style='color:#000000'>Documentation Done</span>\")\n65(\"<span style='color:#000000'>Loan Disbursed</span>\")\n66(\"<span style='color:#000000'>Loan Closed</span>\")\n52 --> 53\n53 --> 54\n54 --> 55\n54 --> 102\n55 --> 56\n56 --> 62\n56 --> 57\n57 --> 58\n58 --> 59\n58 --> 60\n59 --> 61\n61 --> 63\n63 --> 64\n64 --> 65\n65 --> 66\nstyle 52 fill:#ECECFF, stroke:#CCCCFF;\nstyle 55 fill:#ECECFF, stroke:#CCCCFF;\nstyle 64 fill:#ECECFF, stroke:#CCCCFF;\nstyle 65 fill:#ECECFF, stroke:#CCCCFF;\nstyle 102 fill:#ECECFF, stroke:#CCCCFF;\nstyle 61 fill:#ECECFF, stroke:#CCCCFF;\nstyle 58 fill:#ECECFF, stroke:#CCCCFF;\nstyle 60 fill:#ECECFF, stroke:#CCCCFF;\nstyle 57 fill:#ECECFF, stroke:#CCCCFF;\nstyle 56 fill:#ECECFF, stroke:#CCCCFF;\nstyle 63 fill:#ECECFF, stroke:#CCCCFF;\nstyle 54 fill:#ECECFF, stroke:#CCCCFF;\nstyle 59 fill:#ECECFF, stroke:#CCCCFF;\nstyle 53 fill:#ECECFF, stroke:#CCCCFF;\nstyle 62 fill:#ECECFF, stroke:#CCCCFF;\nstyle 66 fill:#ECECFF, stroke:#CCCCFF;";

    var svg = mermaidAPI.render(result);
    $("#productionFlowChart").html(svg);
  }

  close() {
    $("#productionFlowChart").html("");
    this.dialogRef.close();
  }

  ngOnDestroy() {
    
  }

  lazyLoadMermaid(): Observable<any> {
    return forkJoin([
      this.loadScript('assets/mermaidAPI.min.js'),
      this.loadStyle('assets/mermaid.css')
    ]);
  }

  private loadScript(url: string): Observable<any> {
    if (this._loadedLibraries[url]) {
      return this._loadedLibraries[url].asObservable();
    }

    this._loadedLibraries[url] = new ReplaySubject();
    const script = this.document.createElement('script');
    script.type = 'text/javascript';
    script.async = true;
    script.src = url;
    script.onload = () => {
      this._loadedLibraries[url].next();
      this._loadedLibraries[url].complete();
    };

    this.document.body.appendChild(script);
    return this._loadedLibraries[url].asObservable();
  }

  private loadStyle(url: string): Observable<any> {
    if (this._loadedLibraries[url]) {
      return this._loadedLibraries[url].asObservable();
    }

    this._loadedLibraries[url] = new ReplaySubject();

    const style = this.document.createElement('link');
    style.type = 'text/css';
    style.href = url;
    style.rel = 'stylesheet';
    style.onload = () => {
      this._loadedLibraries[url].next();
      this._loadedLibraries[url].complete();
    };

    const head = document.getElementsByTagName('head')[0];
    head.appendChild(style);
    return this._loadedLibraries[url].asObservable();
  }

  callLazyLoadMermaid() {
    this.lazyLoadMermaid().subscribe(data => {
      var result = "graph TD; " +
        "A-->B;" +
        "A-->C;" +
        "B-->D;" +
        "C-->D;";



      mermaidAPI = this.document.defaultView.mermaidAPI;

      //Dummy Chart
      //this.drawDummyChart();

      //Actual call to server to fetch the chart details
      this.getApplicationChartData(this.applicationID, this.transitionId);
    });
  }

  getApplicationChartData(applicationId: number, transitionId: number) {
    this.applicationService.getApplicationChartDetails(applicationId, transitionId).subscribe(
      data => {
        if (data.success) {
          var svg = mermaidAPI.render(data.result);
          $("#productionFlowChart").html(svg);
        }
        else {
          $("#productionFlowChart").html("Error Loading Workflow chart");
        }
      },
      error => {
        $("#productionFlowChart").html("Error Loading Workflow chart");
      }
    );
  }
}



