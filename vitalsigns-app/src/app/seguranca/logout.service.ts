import { AuthService } from './auth.service';
import { AuthHttp } from 'angular2-jwt';
import { Injectable } from '@angular/core';

@Injectable()
export class LogoutService {

  // tokensRenokeUrl = 'http://localhost:8080/tokens/revoke';
  // tokensRenokeUrl = 'http://vitalsigns-api-e-glicemia.apps.na46.prod.nextcle.com/tokens/revoke';
  tokensRenokeUrl = 'https://victor-eglicemia.herokuapp.com/tokens/revoke';

  constructor(
    private http: AuthHttp,
    private auth: AuthService
  ) { }

  logout() {
    return this.http.delete(this.tokensRenokeUrl, { withCredentials: true })
      .toPromise()
      .then(() => {
        this.auth.limparAccessToken();
      });
  }

}
