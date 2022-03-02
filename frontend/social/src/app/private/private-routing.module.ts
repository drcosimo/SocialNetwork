import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { VisualizzaPostComponent } from '../visualizza-post/visualizza-post.component';
import { PubblicaPostComponent } from '../pubblica-post/pubblica-post.component';
import { ProfiloComponent } from '../profilo/profilo.component';
import { HomepageComponent } from '../homepage/homepage.component';

const routes: Routes = [{
  path: '',
  component: HomepageComponent,
  children: [
    {
      path : 'visualizzaPost',
      component: VisualizzaPostComponent
    },
    {
      path : 'pubblicaPost',
      component : PubblicaPostComponent
    },
    {
      path : 'profilo',
      component: ProfiloComponent
    },
    {
      path: "**",
      component: VisualizzaPostComponent
    },
    {
      path:"pubblicaPost/:messaggio",
      component : HomepageComponent
    }
  ]
}]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class PrivateRoutingModule { }
