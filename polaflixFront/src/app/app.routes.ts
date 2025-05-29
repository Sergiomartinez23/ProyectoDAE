import { Routes } from '@angular/router';
import { InicioComponent } from './inicio/inicio.component';
import { AgregarSerieComponent } from './agregar-serie/agregar-serie.component';
import { VerCargosComponent } from './ver-cargos/ver-cargos.component';

export const routes: Routes = [
    {path: "", component: InicioComponent},
    {path: "inicio", component: InicioComponent},
    {path: "anhadirSerie", component: AgregarSerieComponent},
    {path: "verCargos", component: VerCargosComponent}
];
