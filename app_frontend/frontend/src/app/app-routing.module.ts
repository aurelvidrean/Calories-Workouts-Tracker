import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { DialogComponent } from './components/dialog/dialog.component';
import { EctomorphComponent } from './components/ectomorph/ectomorph.component';
import { EndomoprhComponent } from './components/endomoprh/endomoprh.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MealPlanGainComponent } from './components/meal-plan-gain/meal-plan-gain.component';
import { MealPlanMantainComponent } from './components/meal-plan-mantain/meal-plan-mantain.component';
import { MealPlanComponent } from './components/meal-plan/meal-plan.component';
import { MesomorphComponent } from './components/mesomorph/mesomorph.component';
import { RegisterComponent } from './components/register/register.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'register', component: RegisterComponent},
  { path: 'login', component: LoginComponent},
  { path: 'dialog', component: DialogComponent },
  { path: 'meal-plan', component: MealPlanComponent, data: { label: 'You have opted for lose weight plan' } },
  { path: 'meal-plan-gain', component: MealPlanGainComponent, data: { label: 'You have opted for gain weight plan' } },
  { path: 'meal-plan-mantain', component: MealPlanMantainComponent},
  { path: 'ectomorph', component: EctomorphComponent},
  { path: 'endomoprh', component: EndomoprhComponent},
  { path: 'mesomorph', component: MesomorphComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes), BrowserModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
