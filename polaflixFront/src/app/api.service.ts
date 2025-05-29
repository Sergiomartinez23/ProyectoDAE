import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})

export class ApiService {
  private apiUrl = 'http://localhost:8080'; 

  constructor(private http: HttpClient) { }

  getSeriesNombre(filtro: String) {
    return this.http.get<any[]>(`${this.apiUrl}/series/${filtro}`);
  }
  getSeriesNombreEmpieza(filtro: String) {
    return this.http.get<any[]>(`${this.apiUrl}/seriesEmpieza/${filtro}`);
  }
  anhadirSerie(serie: any) {
    return this.http.put<any>(`${this.apiUrl}/usuarioSeriesPorVer/1`, serie);
  }

  getSeriesPorVer() {
    return this.http.get<any[]>(`${this.apiUrl}/usuarioSeriesPorVer/1`);
  }
  getSeriesEmpezadas() {
    return this.http.get<any[]>(`${this.apiUrl}/usuarioSeriesEmpezadas/1`);
  }
  getSeriesTerminadas() {
    return this.http.get<any[]>(`${this.apiUrl}/usuarioSeriesTerminadas/1`);
  }
}