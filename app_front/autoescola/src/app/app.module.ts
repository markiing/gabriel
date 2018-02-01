import { AppComponent } from './app.component';
import { AlunosService } from './alunos/alunos.service';
import { routing } from './app.routing';
import { AlunosComponent } from './alunos/alunos.component';
import { AlunoDetailComponent } from './alunos/alunos.detail/aluno-detail.component';
import { NgModule } from '@angular/core/src/metadata/ng_module';


@NgModule({
  declarations: [
    AppComponent,
    AlunosComponent,
    AlunoDetailComponent
  ],
  imports: [
    routing
  ],
  providers: [
    AlunosService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
