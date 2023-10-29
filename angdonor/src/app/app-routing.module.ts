import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SubscriberComponent } from './subscriber/subscriber.component';
import { StatusComponent } from './status/status.component';
import { ActivationComponent } from './activation/activation.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path:'caf' , component:SubscriberComponent},
  {path:'status', component:StatusComponent},
  {path:'activation', component:ActivationComponent},
  {path:'login',component:LoginComponent},
  {path:'home',component:HomeComponent},
  {path:'', redirectTo:'/home' , pathMatch:`full`}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
