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

const appRoutes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'userhome', component: UserhomeComponent, canActivate: [AuthGuard] },
    { path: 'managerhome', component: ManagerhomeComponent, canActivate: [AuthGuard] },
    { path: 'config', component: ConfigComponent },
    { path: 'newloan', component: NewloanComponent },
    { path: 'applicationDetail/:id', component: ApplicationdetailComponent },
    { path: 'applicationDetailUser/:id', component: ApplicationdetailuserComponent },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);