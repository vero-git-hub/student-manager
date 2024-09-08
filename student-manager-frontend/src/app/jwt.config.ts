import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable, Provider } from '@angular/core';
import { JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

export function tokenGetter() {
  return localStorage.getItem('access_token');
}

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = tokenGetter();
    if (token) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }
    return next.handle(req);
  }
}

export function provideJwtClient(): Provider[] {
  return [
    { provide: JWT_OPTIONS, useValue: { tokenGetter, allowedDomains: ['localhost:8080'], disallowedRoutes: ['http://localhost:8080/authenticate', 'http://localhost:8080/register'] } },
    { provide: JwtHelperService, useFactory: () => new JwtHelperService() }
  ];
}

export const jwtInterceptor: Provider = {
  provide: HTTP_INTERCEPTORS,
  useClass: JwtInterceptor,
  multi: true
};
