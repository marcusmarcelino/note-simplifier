import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home'},
  {
    path: 'home',
    loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule)
  },
  {
    path: 'profile',
    pathMatch: 'full',
    loadChildren: () => import('./modules/profile/profile.module').then(m => m.ProfileModule)
  },
  // {
  //   path: 'login',
  //   pathMatch: 'full',
  //   loadChildren: () => import('./modules/auth/auth.module').then(m => m.AuthModule)
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
