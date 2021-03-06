import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

// used to create fake backend
import { fakeBackendProvider } from './_helpers/fake-backend';

import { AppComponent } from './app.component';
import { routing } from './app.routing';

import { AlertComponent } from './_components/alert.component';
import { JwtInterceptor } from './_helpers/jwt.interceptor';
import { ErrorInterceptor } from './_helpers/error.interceptor';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserhomeComponent } from './userhome/userhome.component';
import { ConfigComponent } from './config/config.component';
import { ManagerhomeComponent } from './managerhome/managerhome.component';
import { NewloanComponent } from './newloan/newloan.component';
import { ApplicationdetailComponent } from './applicationdetail/applicationdetail.component';
import { ApplicationdetailuserComponent } from './applicationdetailuser/applicationdetailuser.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ErrorComponent } from './error/error.component';
import { WorkflowactionComponent } from './workflowaction/workflowaction.component';
import { WorkflowviewComponent } from './workflowview/workflowview.component';
import { MatDialogModule, MatButtonModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    AlertComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    UserhomeComponent,
    ConfigComponent,
    ManagerhomeComponent,
    NewloanComponent,
    ApplicationdetailComponent,
    ApplicationdetailuserComponent,
    DashboardComponent,
    ErrorComponent,
    WorkflowactionComponent,
    WorkflowviewComponent,
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    routing,
    MatDialogModule,
    MatButtonModule,
    BrowserAnimationsModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },

        // provider used to create fake backend
        fakeBackendProvider
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
