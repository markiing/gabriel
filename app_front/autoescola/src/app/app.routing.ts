
import { AlunosComponent } from './alunos/alunos.component';
import { AlunoDetailComponent } from './alunos/alunos.detail/aluno-detail.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

// Components

// /Components

const APP_ROUTES: Routes = [
  {
    path: 'alunos',
    component: AlunosComponent
  },
  {
    path: 'alunos/aluno',
    component: AlunoDetailComponent
  },
  
  {
    path: '404',
    component: NotFoundComponent
  },
  {
    path: '**',
    redirectTo: '/404'
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(APP_ROUTES);
