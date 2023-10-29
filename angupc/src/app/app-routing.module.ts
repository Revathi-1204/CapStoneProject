import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UpcComponent } from './upc/upc.component';
import { StatusComponent } from './status/status.component';
import { RequestComponent } from './request/request.component';
import { HomeComponent } from './home/home.component';
import { DeactivateComponent } from './deactivate/deactivate.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {path:'upc' , component:UpcComponent},
  {path:'status', component:StatusComponent},
  {path:'request', component:RequestComponent},
  {path: 'home',component:HomeComponent},
  {path:'deactivate',component:DeactivateComponent},
  {path:'login',component:LoginComponent},
  {path:'', redirectTo:'/home' , pathMatch:`full`}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
