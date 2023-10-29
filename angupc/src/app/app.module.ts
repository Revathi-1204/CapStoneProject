import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UpcComponent } from './upc/upc.component';
import { NavbarComponent } from './navbar/navbar.component';
import { StatusComponent } from './status/status.component';
import { RequestComponent } from './request/request.component';
import { HomeComponent } from './home/home.component';
import { DeactivateComponent } from './deactivate/deactivate.component';
import { LoginComponent } from './login/login.component';


@NgModule({
  declarations: [
    AppComponent,
    UpcComponent,
    NavbarComponent,
    StatusComponent,
    RequestComponent,
    HomeComponent,
    DeactivateComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
