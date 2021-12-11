import { AfterViewInit, Component } from '@angular/core';
import { AuthService } from './modules/auth/services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements AfterViewInit {

  title = 'web';
  menu: boolean = false;

  constructor(
    private authService: AuthService
  ) { }

  ngAfterViewInit(): void {
    this.authService.showMenu.subscribe(
      (isAuthenticated) => { this.menu = isAuthenticated; }
    );
  }
}
