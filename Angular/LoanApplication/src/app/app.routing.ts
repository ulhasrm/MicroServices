import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthGuard } from './_helpers/auth.guard';
import { UserhomeComponent } from './userhome/userhome.component';
import { ConfigComponent } from './config/config.component';
import { ManagerhomeComponent } from './managerhome/managerhome.component';
import { NewloanComponent } from './newloan/newloan.component';
import { ApplicationdetailComponent } from './applicationdetail/applicationdetail.component';
import { ApplicationdetailuserComponent } from './applicationdetailuser/applicationdetailuser.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ErrorComponent } from './error/error.component';
import { WorkflowviewComponent } from './workflowview/workflowview.component';

const appRoutes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'login/:role', component: LoginComponent },
    { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
    { path: 'register', component: RegisterComponent },
    { path: 'userhome', component: UserhomeComponent, canActivate: [AuthGuard] },
    { path: 'managerhome', component: ManagerhomeComponent, canActivate: [AuthGuard] },
    { path: 'config', component: ConfigComponent },
    { path: 'newloan', component: NewloanComponent },
    { path: 'applicationDetail/:id', component: ApplicationdetailComponent },
    { path: 'applicationDetailUser/:id', component: ApplicationdetailuserComponent },
    { path: 'error', component: ErrorComponent },
    { path: 'workflowview', component: WorkflowviewComponent },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);