import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { NgFor } from '@angular/common';
@Component({
  selector: 'app-inicio',
  imports: [NgFor],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css'
})
export class InicioComponent {

  seriesPorVer: any[] = [];
  seriesEmpezadas: any[] = [];
  seriesTerminadas: any[] = [];

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.api.getSeriesPorVer().subscribe({next: (data) => {
      this.seriesPorVer = data;
    }});
    this.api.getSeriesEmpezadas().subscribe({next: (data) => {
      this.seriesEmpezadas = data;
      console.log(this.seriesEmpezadas);
    }});
    this.api.getSeriesTerminadas().subscribe({next: (data) => {
      this.seriesTerminadas = data;
    }});  
  }




}
