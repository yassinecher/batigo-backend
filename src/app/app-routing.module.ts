import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommandeComponent } from './commande/commande.component';
import { LivraisonComponent } from './livraison/livraison.component';

const routes: Routes = [
  { path: 'commande', component: CommandeComponent },
  { path: 'livraison', component: LivraisonComponent }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
