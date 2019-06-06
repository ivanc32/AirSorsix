import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Injectable()
export class AuthGuard implements CanActivate {

    toRedirect: boolean;

    constructor(private authService: AuthenticationService, private router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
      const url: string = state.url;
      this.authService.isLoggedIn().subscribe(it => {
        if (url.includes('login')) {
          this.toRedirect = !it;
        } else {
          this.toRedirect = it;
        }
        if (this.toRedirect !== true) {
          this.router.navigateByUrl('/home');
        }
      });
      return true;
    }
}

