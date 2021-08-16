import { Injectable } from '@angular/core';

import { AuthHttp } from 'angular2-jwt';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class CategoriaService {

  // categoriasUrl = 'http://localhost:8080/categorias';
  categoriasUrl = 'http://vitalsigns-api-e-glicemia.apps.na46.prod.nextcle.com/categorias';
  // categoriasUrl = 'https://victor-eglicemia.herokuapp.com/categorias';

  constructor(private http: AuthHttp) { }

  listarTodas(): Promise<any> {
    return this.http.get(this.categoriasUrl)
      .toPromise()
      .then(response => response.json());
  }

}
