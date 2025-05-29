import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgFor } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../api.service';
@Component({
  selector: 'app-agregar-serie',
  imports: [NgFor, CommonModule, RouterModule, FormsModule],
  templateUrl: './agregar-serie.component.html',
  styleUrl: './agregar-serie.component.css',
  providers: [ApiService]
})
export class AgregarSerieComponent {
  letras: string[] = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
  busquedaActual: string = "";
  showDescripcion: boolean = false;
  series!: any[];
  haySeries: boolean;
  constructor(private api: ApiService) { 
    this.haySeries = true;
  }

  ngOnInit() {
    this.buscarEmpieza("A");
    
  }
  mostrarDesc(serie: any) {
    serie.showDescripcion = !serie.showDescripcion;
  }

  buscar(filtro: String) {
    console.log("Buscando: " + filtro);
    this.api.getSeriesNombre(filtro).subscribe({
      next: (data) => {
        this.haySeries = true;
        this.series = data;
        console.log(this.series);
        this.series.forEach(element => {
          element.showDescripcion = false;
        });
        this.series= this.ordenarSeries(this.series);
      },
      error: (error: any) => {
        this.haySeries= false;
      }
    });
    
  }

  buscarEmpieza(filtro: String) {
    console.log("Buscando: " + filtro);
    this.api.getSeriesNombreEmpieza(filtro).subscribe({
      next: (data) => {
        this.haySeries = true;
        this.series = data;
        console.log(this.series);
        this.series.forEach(element => {
          element.showDescripcion = false;
        });
        this.series= this.ordenarSeries(this.series);
      },
      error: (error: any) => {
        this.haySeries= false;
      }
    });
    
  }

  // Add this method inside your AgregarSerieComponent class
  getDirectoresNombres(serie: any): string {
      return serie.director.map((d: any) => d.nombreCompleto).join(', ');
  }

  getActoresNombres(serie: any): string {
    return serie.actores.map((a: any) => a.nombreCompleto).join(', ');
  }

  anhadir(serie: any) {
    console.log("Añadiendo serie: " + serie.nombre);
    this.api.anhadirSerie(serie).subscribe({
      next: () => {
        console.log("Serie añadida correctamente");
      },
      error: (error: any) => {
        console.error("Error al añadir la serie", error);
      }
    });
  }

  ordenarSeries(series: any[]): any[] {
    return series.sort((a,b) => a.titulo > b.titulo ? 1 : -1);
  }
  
}
