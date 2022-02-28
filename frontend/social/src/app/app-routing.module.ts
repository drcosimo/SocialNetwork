import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { ProfiloComponent } from './profilo/profilo.component';
import { PubblicaPostComponent } from './pubblica-post/pubblica-post.component';
import { VisualizzaPostComponent } from './visualizza-post/visualizza-post.component';

const routes: Routes = [
  {
    path : 'login',
    component : LoginComponent
  },
  {
    path : 'private',
    loadChildren: () => import('./private/private.module').then(m => m.PrivateModule)
  },
  
  // mando alla pagina di login per default
  {
    path :'',
    redirectTo: 'login',
    // applica questa regola solo quando il percorso è esattamente stringa vuota
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
