import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfileRoutingModule } from './profile-routing.module';
import { PersonalDataComponent } from './personal-data/personal-data.component';
import { ProfileComponent } from './profile/profile.component';
import { PasswordComponent } from './password/password.component';


@NgModule({
  declarations: [
    PersonalDataComponent,
    ProfileComponent,
    PasswordComponent
  ],
  imports: [
    CommonModule,
    ProfileRoutingModule
  ]
})
export class ProfileModule { }
