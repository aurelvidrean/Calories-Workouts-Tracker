import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from './services/user-service';
import { HomeComponent } from './components/home/home.component';
import { DialogComponent } from './components/dialog/dialog.component';
import { MealPlanComponent } from './components/meal-plan/meal-plan.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MealPlanGainComponent } from './components/meal-plan-gain/meal-plan-gain.component';
import { MealPlanMantainComponent } from './components/meal-plan-mantain/meal-plan-mantain.component';
import { EctomorphComponent } from './components/ectomorph/ectomorph.component';
import { EndomoprhComponent } from './components/endomoprh/endomoprh.component';
import { MesomorphComponent } from './components/mesomorph/mesomorph.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    DialogComponent,
    MealPlanComponent,
    MealPlanGainComponent,
    MealPlanMantainComponent,
    EctomorphComponent,
    EndomoprhComponent,
    MesomorphComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
