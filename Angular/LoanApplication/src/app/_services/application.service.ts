import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../models/user';
import { Application } from '@/models/application';
import { environment } from '../../environments/environment';
import { map } from 'rxjs/operators';
import { LoanType } from '@/models/loantype';
import { WorkflowTransition } from '@/models/workflowaction';
import { WorkFlowActionResult } from '@/models/WorkflowActionResult';
import { PertChartResponse } from '@/models/PertChartResponse';

@Injectable({ providedIn: 'root' })
export class ApplicationService {
    loanAppUrl = environment.loanappUrl;
    constructor(private http: HttpClient) { }

    getAll(userId: any) {
        var url = this.loanAppUrl + "SystemUser/" + userId + "/Application";
        return this.http.get<Application[]>(url);
    }

    getAllForAdmin() {
        var url = this.loanAppUrl + "Application";
        return this.http.get<Application[]>(url);
    }

    getById(id: number) {
        var url = this.loanAppUrl + "/Application/" + id;
        return this.http.get<Application>(url);
    }

    create(loanType: String, amount: DoubleRange, mobileNumber: String, userId: number) {
        var url = this.loanAppUrl + "SystemUser/" + userId + "/Application"
        return this.http.post<Application>(
            url,
            {
                "loanType": loanType,
                "amount": amount
            },
        ).pipe(map(result => {
            return result;
        }));
    }

    update(application: Application) {
        var url = this.loanAppUrl + "/Application";
        return this.http.put(url, application).pipe(map(result => {
            return result;
        }));
    }

    delete(id: number) {
        return this.http.delete(`${config.apiUrl}/users/${id}`);
    }

    getAllLoanTypes() {
        var url = this.loanAppUrl + "LoanType";
        return this.http.get<LoanType[]>(url);
    }

    getActionsForAplication(applicationId: number, userId: number) {
        var url = this.loanAppUrl + "WorkflowTransition/" + applicationId + "/" + userId;
        alert(url);
        return this.http.get<WorkflowTransition[]>(url);
    }

    getNextActionsForAplication(applicationId: number, userId: number) {
        var url = this.loanAppUrl + "WorkflowTransition/NextAction/" + applicationId + "/" + userId;
        //var url = this.loanAppUrl + "WorkflowTransition" + applicationId;
        return this.http.get<WorkflowTransition[]>(url);
    }


    performAction(applicationId: number, transactionId: number) {
        var url = this.loanAppUrl + "Application/PerformAction";
        return this.http.post<WorkFlowActionResult>(url, {
            applicationId: applicationId,
            transactionId: transactionId
        }).pipe(map(result => {
            return result;
        }));
    }

    getApplicationChartDetails(applicationId: number, transactionId: number) {
        var url = this.loanAppUrl + "Application/WorkFlowChart/"+applicationId+"/"+transactionId;
        return this.http.get<PertChartResponse>(url).pipe(map(result => {
            return result;
        }));
    }
}